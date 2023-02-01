package hu.unideb.inf.globus_komissio.databases.localcontainers;

public class RightsContainer {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RightsContainer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
