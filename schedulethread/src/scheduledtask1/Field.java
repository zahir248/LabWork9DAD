// activity 7.1

package scheduledtask1;

public class Field {
    private boolean primaryKey;
    private String name;
    private String type;

    public Field(boolean primaryKey, String name, String type) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.type = type;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String generateFieldSQLStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ").append(type);
        if (primaryKey) {
            sb.append(" PRIMARY KEY");
        }
        return sb.toString();
    }


}
