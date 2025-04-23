import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {
    private final List<Entity> entities = new ArrayList<>();
    private final HashMap<String, Device> deviceMap = new HashMap<>();
    private final ArrayList<Rule> rules = new ArrayList<>();

    public String addDevice(String type, String name, String protocol) {
        if (deviceMap.containsKey(name)) return "duplicate device name";

        if (!type.equals("light") && !type.equals("thermostat")) return "invalid input";

        if (!protocol.equals("WiFi") && !protocol.equals("Bluetooth")) return "invalid input";

        Device device = new Device(type, name, protocol);
        entities.add(device);
        deviceMap.put(name, device);
        return "device added successfully";
    }

    public String setDevice(String name, String property, String value) {
        Device device = deviceMap.get(name);
        if (device == null) return "device not found";

        try {
            switch (device.getType()) {
                case "light":
                    if (property.equals("status")) {
                        device.setStatus(value.equals("on"));
                    } else if (property.equals("brightness")) {
                        int brightness = Integer.parseInt(value);
                        if (brightness < 0 || brightness > 100) return "invalid value";
                        device.setBrightness(brightness);
                    } else {
                        return "invalid property";
                    }
                    break;

                case "thermostat":
                    if (property.equals("status")) {
                        device.setStatus(value.equals("on"));
                    } else if (property.equals("temperature")) {
                        int temperature = Integer.parseInt(value);
                        if (temperature < 10 || temperature > 30) return "invalid value";
                        device.setTemperature(temperature);
                    } else {
                        return "invalid property";
                    }
                    break;

                default:
                    return "invalid device type";
            }

            return "device updated successfully";
        } catch (NumberFormatException e) {
            return "invalid value";
        }
    }

    public String removeDevice(String name) {
        Device device = deviceMap.get(name);
        if (device == null) return "Error: device not found";

        entities.remove(device);
        deviceMap.remove(name);

        rules.removeIf(rule -> rule.getDeviceName().equals(name));

        return "device removed successfully";
    }


    public List<String> listDevices() {
        List<String> result = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity instanceof Device) {
                result.add(entity.toString());
            }
        }
        if (result.isEmpty()) result.add("");
        return result;
    }

    public String addRule(String deviceName, String time, String action) {
        if (!deviceMap.containsKey(deviceName)) return "device not found";

        if (!isValidTime(time)) return "invalid time";

        if (!action.equals("on") && !action.equals("off")) return "invalid action";

        for (Rule rule : rules) {
            if (rule.getDeviceName().equals(deviceName) && rule.getTime().equals(time)) {
                return "duplicate rule";
            }
        }

        rules.add(new Rule(deviceName, time, action));
        return "rule added successfully";
    }

    public List<String> listRules() {
        List<String> result = new ArrayList<>();
        for (Rule rule : rules) {
            result.add(rule.toString());
        }
        if (result.isEmpty()) result.add("");
        return result;
    }

    public String checkRules(String time) {
        if (!isValidTime(time)) return "invalid time";

        for (Rule rule : rules) {
            if (rule.getTime().equals(time)) {
                applyAction(rule.getDeviceName(), rule.getAction());
            }
        }

        return "rules checked";
    }

    private boolean isValidTime(String time) {
        if (time.length() != 5 || time.charAt(2) != ':' ||
                !Character.isDigit(time.charAt(0)) || !Character.isDigit(time.charAt(1)) ||
                !Character.isDigit(time.charAt(3)) || !Character.isDigit(time.charAt(4))) {
            return false;
        }

        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));

        return hour >= 0 && hour < 24 && minute >= 0 && minute < 60;
    }

    private void applyAction(String deviceName, String action) {
        Device device = deviceMap.get(deviceName);
        if (device != null) {
            device.setStatus(action.equals("on"));
        }
    }
}
