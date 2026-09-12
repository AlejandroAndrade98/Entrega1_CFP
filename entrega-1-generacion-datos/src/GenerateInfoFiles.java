import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Generates coherent, pseudo-random input files for the sales reporting project.
 *
 * <p>The generated files are stored in {@code data/input}. This class is the
 * executable required for delivery 1 and deliberately does not request data
 * from the user.</p>
 *
 * @author Alejandro Anibal Andrade
 * @version 1.0
 */
public class GenerateInfoFiles {

    /** Folder used by the second delivery to read the generated input files. */
    private static final Path INPUT_DIRECTORY = Paths.get("data", "input");

    /** File containing the master list of available products. */
    private static final Path PRODUCTS_FILE = INPUT_DIRECTORY.resolve("products.csv");

    /** File containing the master list of salespeople. */
    private static final Path SALESMEN_INFO_FILE = INPUT_DIRECTORY.resolve("salesmen.csv");

    /** Seed product names used to generate coherent product information. */
    private static final String[] PRODUCT_NAMES = {
        "Soccer Ball", "Training Cone", "Sports Bottle", "Team Jersey",
        "Goalkeeper Gloves", "Shin Guards", "Sports Bag", "Whistle",
        "Training Ladder", "Captain Armband", "Sports Socks", "Stopwatch"
    };

    /** First names used when creating salesperson records. */
    private static final String[] FIRST_NAMES = {
        "Sofia", "Mateo", "Valentina", "Santiago", "Isabella", "Samuel",
        "Camila", "Daniel", "Mariana", "Nicolas", "Luciana", "Sebastian"
    };

    /** Last names used when creating salesperson records. */
    private static final String[] LAST_NAMES = {
        "Garcia", "Rodriguez", "Martinez", "Lopez", "Gonzalez", "Perez",
        "Hernandez", "Torres", "Ramirez", "Diaz", "Castro", "Morales"
    };

    /** Product identifiers available for the generated sales files. */
    private static final List<String> PRODUCT_IDS = new ArrayList<String>();

    /** Random number generator used to create pseudo-random but valid data. */
    private static final Random RANDOM = new Random();

    /**
     * Generates all input files required by the reporting program.
     *
     * @param args command-line arguments, not used by this program
     */
    public static void main(String[] args) {
        try {
            prepareInputDirectory();
            createProductsFile(10);
            List<Salesman> salesmen = createSalesManInfoFile(6);

            for (Salesman salesman : salesmen) {
                int salesCount = randomBetween(8, 18);
                createSalesMenFile(salesCount, salesman.getFullName(), salesman.getId());
            }

            System.out.println("Input files generated successfully in: "
                    + INPUT_DIRECTORY.toAbsolutePath());
        } catch (IOException exception) {
            System.err.println("The input files could not be generated: "
                    + exception.getMessage());
        }
    }

    /**
     * Creates a sales file for one salesperson.
     *
     * <p>The first line identifies the salesperson. Every following line has a
     * valid product id and a positive quantity, separated by semicolons.</p>
     *
     * @param randomSalesCount number of sales lines to generate
     * @param name salesperson full name, used to identify the generated file
     * @param id salesperson document number
     * @throws IOException when the file cannot be written
     */
    public static void createSalesMenFile(int randomSalesCount, String name, long id)
            throws IOException {
        if (randomSalesCount <= 0) {
            throw new IllegalArgumentException("The sales count must be greater than zero.");
        }
        if (PRODUCT_IDS.isEmpty()) {
            throw new IllegalStateException("Products must be created before sales files.");
        }

        String fileName = "sales_" + normalizeForFileName(name) + "_" + id + ".csv";
        Path salesFile = INPUT_DIRECTORY.resolve(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(salesFile,
                StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write("CC;" + id);
            writer.newLine();

            for (int saleIndex = 0; saleIndex < randomSalesCount; saleIndex++) {
                String productId = PRODUCT_IDS.get(RANDOM.nextInt(PRODUCT_IDS.size()));
                int quantity = randomBetween(1, 8);
                writer.write(productId + ";" + quantity);
                writer.newLine();
            }
        }
    }

    /**
     * Creates a products file with unique ids, names and positive prices.
     *
     * @param productsCount number of products to create, from 1 to the number
     *        of product names available in this generator
     * @throws IOException when the file cannot be written
     */
    public static void createProductsFile(int productsCount) throws IOException {
        if (productsCount <= 0 || productsCount > PRODUCT_NAMES.length) {
            throw new IllegalArgumentException("Products count must be between 1 and "
                    + PRODUCT_NAMES.length + ".");
        }

        PRODUCT_IDS.clear();
        try (BufferedWriter writer = Files.newBufferedWriter(PRODUCTS_FILE,
                StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            for (int productIndex = 0; productIndex < productsCount; productIndex++) {
                String productId = String.format(Locale.US, "P%03d", productIndex + 1);
                int price = randomBetween(15000, 180000);
                PRODUCT_IDS.add(productId);
                writer.write(productId + ";" + PRODUCT_NAMES[productIndex] + ";" + price);
                writer.newLine();
            }
        }
    }

    /**
     * Creates the master file with salesperson identification and names.
     *
     * @param salesmanCount number of salespeople to create
     * @return the generated salespeople, used to create their sales files
     * @throws IOException when the file cannot be written
     */
    public static List<Salesman> createSalesManInfoFile(int salesmanCount) throws IOException {
        if (salesmanCount <= 0) {
            throw new IllegalArgumentException("Salesman count must be greater than zero.");
        }

        List<Salesman> salesmen = new ArrayList<Salesman>();
        try (BufferedWriter writer = Files.newBufferedWriter(SALESMEN_INFO_FILE,
                StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            for (int salesmanIndex = 0; salesmanIndex < salesmanCount; salesmanIndex++) {
                long id = 1000000000L + salesmanIndex + 1;
                String firstName = FIRST_NAMES[salesmanIndex % FIRST_NAMES.length];
                String lastName = LAST_NAMES[salesmanIndex % LAST_NAMES.length];
                Salesman salesman = new Salesman(id, firstName, lastName);
                salesmen.add(salesman);

                writer.write("CC;" + salesman.getId() + ";" + salesman.getFirstName()
                        + ";" + salesman.getLastName());
                writer.newLine();
            }
        }
        return salesmen;
    }

    /**
     * Ensures the input folder exists before creating its files.
     *
     * @throws IOException when the directory cannot be created
     */
    private static void prepareInputDirectory() throws IOException {
        Files.createDirectories(INPUT_DIRECTORY);
    }

    /**
     * Returns a random integer in the inclusive interval supplied.
     *
     * @param minimum lower bound of the interval
     * @param maximum upper bound of the interval
     * @return pseudo-random integer between minimum and maximum
     */
    private static int randomBetween(int minimum, int maximum) {
        return minimum + RANDOM.nextInt(maximum - minimum + 1);
    }

    /**
     * Converts a person name to a safe file-name fragment.
     *
     * @param value name to normalize
     * @return lower-case name without accents or spaces
     */
    private static String normalizeForFileName(String value) {
        String normalized = Normalizer.normalize(value, Normalizer.Form.NFD);
        return normalized.replaceAll("[^\\p{ASCII}]", "")
                .toLowerCase(Locale.US)
                .replaceAll("[^a-z0-9]+", "_");
    }

    /**
     * Represents one coherent salesperson record used by the generator.
     */
    public static class Salesman {
        private final long id;
        private final String firstName;
        private final String lastName;

        /**
         * Creates a salesperson record.
         *
         * @param id document number
         * @param firstName salesperson first name
         * @param lastName salesperson last name
         */
        public Salesman(long id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        /** @return salesperson document number */
        public long getId() {
            return id;
        }

        /** @return salesperson first name */
        public String getFirstName() {
            return firstName;
        }

        /** @return salesperson last name */
        public String getLastName() {
            return lastName;
        }

        /** @return salesperson full name */
        public String getFullName() {
            return firstName + " " + lastName;
        }
    }
}
