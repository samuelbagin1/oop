package state.machine;

class CountState extends VendingState {
    CountState(VendingMachine machine) {
        super(machine);
        machine.setMessage("zvol pocet");
    }

    @Override
    void setNumber(int number) {
        machine.setCount(number);
        machine.setMessage("pocet: " + number);
        machine.setState(new PayState(machine));
    }

    @Override
    void back() {
        machine.setState(new MenuState(machine));
    }
}
