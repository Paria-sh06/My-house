
public class Device extends Entity {
    private String type;
    private String name;
    private String protocol;

    private boolean status;
    private int brightness;
    private int temperature;

    public Device(String type, String name, String protocol) {
        this.type = type;
        this.name = name;
        this.protocol = protocol;
        this.status = false;
        this.brightness = 0;
        this.temperature = 20;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public int getEntityCode() {
        if ("light".equals(type)) {
            return 1;
        } else if ("thermostat".equals(type)) {
            return 2;
        }
        return -1;
    }

    @Override
    public Entity copy() {
        Device copy = new Device(type, name, protocol);
        copy.setStatus(this.status);
        copy.setBrightness(this.brightness);
        copy.setTemperature(this.temperature);
        return copy;
    }

    @Override
    public String toString() {
        String statusStr = isStatus() ? "on" : "off";
        if (type.equals("light")) {
            return name + " " + statusStr + " " + brightness + "% " + protocol;
        } else { // thermostat
            return name + " " + statusStr + " " + temperature + "°C " + protocol;
        }
    }


}
