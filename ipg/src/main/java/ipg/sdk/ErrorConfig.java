package ipg.sdk;

/**
 * Created by che19 on 2017/9/25.
 */

class ErrorConfig {
    public static final String ErrorMapping = "" +
            "[\n" +
            "  {\n" +
            "    \"ErrorCode\": 1,\n" +
            "    \"ErrorMessage\": \"Credit card number is invalid.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"ErrorCode\": 2,\n" +
            "    \"ErrorMessage\": \"CVV is invalid.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"ErrorCode\": 4,\n" +
            "    \"ErrorMessage\": \"Expiry date is invalid.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"ErrorCode\": 8,\n" +
            "    \"ErrorMessage\": \"Invalid Input (positive number expected).\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"ErrorCode\": 16,\n" +
            "    \"ErrorMessage\": \"Communications failure. Server returned an empty response.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"ErrorCode\": 32,\n" +
            "    \"ErrorMessage\": \"Communications failure. Response from server could not be parsed.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"ErrorCode\": 64,\n" +
            "    \"ErrorMessage\": \"Communications failure. Failed to establish communications.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"ErrorCode\": 128,\n" +
            "    \"ErrorMessage\": \"Communications failure. Unexpected response.\"\n" +
            "  }\n" +
            "]";
}
