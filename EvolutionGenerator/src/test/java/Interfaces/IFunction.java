package Interfaces;

public interface IFunction<TOne, TTwo, R>
{
    public R apply(TOne one, TTwo two);
}
