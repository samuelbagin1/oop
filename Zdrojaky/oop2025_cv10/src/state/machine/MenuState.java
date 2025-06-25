package state.machine;

class MenuState extends VendingState {
    MenuState(VendingMachine machine) {
        super(machine);
        machine.setMessage("vyberte napoj");
    }

    @Override
    void setNumber(int number) {
        machine.setProduct(number);
        machine.setMessage("vybrany napoj: " + machine.getProduct().getName());
        machine.setState(new CountState(machine));
    }

    @Override
    void back() {
    }
}
