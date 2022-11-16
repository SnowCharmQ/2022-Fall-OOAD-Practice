package FactoryMethod;

public class ITArtDesignerFactory implements ITStaffFactoryInterface {
    @Override
    public ITStaff createITStaff() {
        return new ArtDesigner();
    }
}
