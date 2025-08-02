package jms;

public class CalculatorCommand {
    private String operator; // "+", "-", "*"
    private int value;

    public CalculatorCommand() {}
    public CalculatorCommand(String operator, int value) {
        this.operator = operator;
        this.value = value;
    }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    @Override
    public String toString() {
        return "CalculatorCommand{" +
                "operator='" + operator + '\'' +
                ", value=" + value +
                '}';
    }
}
