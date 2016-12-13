package bsr.home;

/**
 * Created by marcin on 07.12.16.
 */
public enum Operation  {
    transfer, deposit, withdraw;

    @Override
    public String toString() {
        return this.name();
    }
}
