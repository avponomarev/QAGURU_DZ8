package ponomarev.andrei;

public class Auto {

    private String name;
    private Integer age;
    private String[] body;
    private Boolean driveUnit;

    private Engine engine;


    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String[] getBody() {
        return body;
    }

    public Boolean getdriveUnit() {
        return driveUnit;
    }

    public Engine getEngine() {
        return engine;
    }

    public class Engine {
        private String type;
        private Integer cylinders;
        private Integer valves;
        private Boolean turbine;

        public String getType() {
            return type;
        }

        public Integer getCylinders() {
            return cylinders;
        }

        public Integer getValves() {
            return valves;
        }

        public Boolean getTurbine() {
            return turbine;
        }
    }
}

