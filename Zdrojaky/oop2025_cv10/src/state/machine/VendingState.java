package state.machine;

abstract class VendingState {
    protected VendingMachine machine;

    public VendingState(VendingMachine machine) {
        this.machine = machine;
    }

    abstract void setNumber(int number);

    abstract void back();

    void pay(int amount) {
        machine.addCredit(amount);
    }
}
