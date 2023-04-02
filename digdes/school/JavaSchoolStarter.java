import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaSchoolStarter {

    private List<Map<String, Object>> collection;

    public JavaSchoolStarter() {

        this.collection = new ArrayList<>();

    }

    public List<Map<String, Object>> executeCommand(String command) {

        String[] commandParts = command.split("\\s+", 3);

        String operation = commandParts[0];

        String columnName = commandParts[1];

        List<Map<String, Object>> result = new ArrayList<>();

        switch (operation) {

            case "insert":

            Map<String, Object> newRow = new HashMap<>();

            newRow.put(columnName, getObjectFromString(commandParts[2]));

            collection.add(newRow);

            result.add(newRow);

            break;

            case "update":

            Object valueToUpdate = getObjectFromString(commandParts[2]);

            for (Map<String, Object> row : collection) {

                if (row.containsKey(columnName)) {

                row.put(columnName, valueToUpdate);

                result.add(row);

            }

        }

        break;

        case "select":

        Object searchValue = getObjectFromString(commandParts[2]);

        for (Map<String, Object> row : collection) {

            if (row.containsKey(columnName) && row.get(columnName).equals(searchValue)) {

                result.add(row);

            }

        }

        break;

        case "delete":

        Object deleteValue = getObjectFromString(commandParts[2]);

        for (int i = collection.size() - 1; i >= 0; i--) {

            Map<String, Object> row = collection.get(i);

            if (row.containsKey(columnName) && row.get(columnName).equals(deleteValue)) {

                result.add(row);

                collection.remove(i);

            }

        }

        break;

        default:

        throw new IllegalArgumentException("Invalid operation: " + operation);

    }

    return result;

}

private Object getObjectFromString(String value) {

    if (value.startsWith("'") && value.endsWith("'")) {

        return value.substring(1, value.length() - 1);

    } else if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {

        return Boolean.valueOf(value);

    } else if (value.contains(".")) {

        return Double.valueOf(value);

    } else {

        return Long.valueOf(value);

    }

}
public static void main(String[] args) {}

}