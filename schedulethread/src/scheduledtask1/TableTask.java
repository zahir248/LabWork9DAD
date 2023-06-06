// activity 7.1

package scheduledtask1;

import java.util.ArrayList;
import java.util.List;

public class TableTask implements Runnable {
    private String name;
    private List<Field> fields;

    public TableTask(String name, List<Field> fields) {
        this.name = name;
        this.fields = fields;
    }
    
    // Getters and setters for the attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void run() {
        String tableSQL = generateTableSQLStatement();
        System.out.println(tableSQL);
    }

    public String generateTableSQLStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(name).append(" (");

        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            sb.append(field.getName()).append(" ").append(field.getType());

            if (field.isPrimaryKey()) {
                sb.append(" PRIMARY KEY");
            }

            if (i < fields.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append(");");
        return sb.toString();
    }

        
        public String generateInsertSQLStatement(List<String> values) {
            if (values.size() != fields.size()) {
                throw new IllegalArgumentException("Number of values provided does not match the number of fields.");
            }

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ").append(name).append(" (");

            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                sb.append(field.getName());
                if (i < fields.size() - 1) {
                    sb.append(", ");
                }
            }

            sb.append(") VALUES (");

            for (int i = 0; i < values.size(); i++) {
                String value = values.get(i);
                sb.append("'").append(value).append("'");
                if (i < values.size() - 1) {
                    sb.append(", ");
                }
            }

            sb.append(");");
            return sb.toString();
        }
        
        public String generateUpdateSQLStatement(String primaryKeyValue, List<String> fieldValues) {
            if (fieldValues.size() != fields.size() - 1) {
                throw new IllegalArgumentException("Number of field values provided does not match the number of fields.");
            }

            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ").append(name).append(" SET ");

            int fieldValuesIndex = 0;
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                if (!field.isPrimaryKey()) {
                    sb.append(field.getName()).append(" = '").append(fieldValues.get(fieldValuesIndex)).append("'");
                    if (fieldValuesIndex < fieldValues.size() - 1) {
                        sb.append(", ");
                    }
                    fieldValuesIndex++;
                }
            }

            sb.append(" WHERE ").append(getPrimaryKeyFieldName()).append(" = '").append(primaryKeyValue).append("';");
            return sb.toString();
        }
        
        public String generateSelectAllSQLStatement() {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ").append(name).append(";");
            return sb.toString();
        }
        
        public String generateSelectByPrimarySQLStatement(String primaryKeyValue) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ").append(name);
            sb.append(" WHERE ").append(getPrimaryKeyFieldName()).append(" = '").append(primaryKeyValue).append("';");
            return sb.toString();
        }
        
        public String generateDeleteByPrimarySQLStatement(String primaryKeyValue) {
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ").append(name);
            sb.append(" WHERE ").append(getPrimaryKeyFieldName()).append(" = '").append(primaryKeyValue).append("';");
            return sb.toString();
        }
        
        public String generateDropTableSQLStatement() {
            StringBuilder sb = new StringBuilder();
            sb.append("DROP TABLE IF EXISTS ").append(name).append(";");
            return sb.toString();
        }


        private String getPrimaryKeyFieldName() {
            for (Field field : fields) {
                if (field.isPrimaryKey()) {
                    return field.getName();
                }
            }
            throw new IllegalStateException("No primary key field found in the list of fields.");
        }
        
        
        public static void main(String[] args) {
            List<Field> fields = new ArrayList<>();
            fields.add(new Field(true, "id", "INT"));
            fields.add(new Field(false, "name", "VARCHAR(255)"));

            TableTask tableTask = new TableTask("my_table", fields);
            
            long startTime, endTime;
            String statement;
            
            String tableSQL = tableTask.generateTableSQLStatement();
            System.out.println(tableSQL); 
            startTime = System.currentTimeMillis();
            statement = tableTask.generateTableSQLStatement();
            endTime = System.currentTimeMillis();
            System.out.println("Execution time: " + (endTime - startTime) + "ms");
            
            startTime = System.currentTimeMillis();
            List<String> values = new ArrayList<>();
            values.add("1");
            values.add("John Doe");
            statement = tableTask.generateInsertSQLStatement(values);
            endTime = System.currentTimeMillis();
            System.out.println(statement);
            System.out.println("Execution time: " + (endTime - startTime) + "ms");
            
            
            startTime = System.currentTimeMillis();
            List<String> fieldValues = new ArrayList<>();
            fieldValues.add("Jane Doe");
            statement = tableTask.generateUpdateSQLStatement("1", fieldValues);
            endTime = System.currentTimeMillis();
            System.out.println(statement);
            System.out.println("Execution time: " + (endTime - startTime) + "ms");
            
            startTime = System.currentTimeMillis();
            statement = tableTask.generateSelectAllSQLStatement();
            endTime = System.currentTimeMillis();
            System.out.println(statement);
            System.out.println("Execution time: " + (endTime - startTime) + "ms");
            
            startTime = System.currentTimeMillis();
            statement = tableTask.generateSelectByPrimarySQLStatement("1");
            endTime = System.currentTimeMillis();
            System.out.println(statement);
            System.out.println("Execution time: " + (endTime - startTime) + "ms");
            
            startTime = System.currentTimeMillis();
            statement = tableTask.generateDeleteByPrimarySQLStatement("1");
            endTime = System.currentTimeMillis();
            System.out.println(statement);
            System.out.println("Execution time: " + (endTime - startTime) + "ms");
            
            startTime = System.currentTimeMillis();
            statement = tableTask.generateDropTableSQLStatement();
            endTime = System.currentTimeMillis();
            System.out.println(statement);
            System.out.println("Execution time: " + (endTime - startTime) + "ms");
            
            

        }


}
