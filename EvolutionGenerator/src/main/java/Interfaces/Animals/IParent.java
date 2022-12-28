package Interfaces.Animals;

public interface IParent {
    IParent copulate(IParent secondParent);
    int getNumberOfChildrens();
}
