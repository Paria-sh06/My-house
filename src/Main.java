import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database db = new Database();

        int q = Integer.parseInt(scanner.nextLine());
        ArrayList<String> output = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts.length == 0) {
                continue;
            }

            String command = parts[0];

            switch (command) {
                case "add_device":
                    if (parts.length != 4) {
                        output.add("Error: Invalid command format");
                        break;
                    }
                    output.add(db.addDevice(parts[1], parts[2], parts[3]));
                    break;

                case "set_device":
                    if (parts.length != 4) {
                        output.add("Error: Invalid command format");
                        break;
                    }
                    output.add(db.setDevice(parts[1], parts[2], parts[3]));
                    break;

                case "remove_device":
                    if (parts.length != 2) {
                        output.add("Error: Invalid command format");
                        break;
                    }
                    output.add(db.removeDevice(parts[1]));
                    break;

                case "list_devices":
                    output.addAll(db.listDevices());
                    break;

                case "add_rule":
                    if (parts.length != 4) {
                        output.add("Error: Invalid command format");
                        break;
                    }
                    output.add(db.addRule(parts[1], parts[2], parts[3]));
                    break;

                case "check_rules":
                    if (parts.length != 2) {
                        output.add("Error: Invalid command format");
                        break;
                    }
                    output.add(db.checkRules(parts[1]));
                    break;

                case "list_rules":
                    output.addAll(db.listRules());
                    break;

                case "exit":
                    output.add("Exiting program...");
                    scanner.close();
                    for (String line : output) {
                        System.out.println(line);
                    }
                    return;

                default:
                    output.add("Error: Unknown command");
            }
        }

        for (String line : output) {
            System.out.println(line);
        }
    }
}
