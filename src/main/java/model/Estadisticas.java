package model;

public class Estadisticas {

    private int totalPlaymobil;
    private double totalCompra;
    private double totalValorActual;

    public Estadisticas() {
    }

    public Estadisticas(int totalPlaymobil,double totalCompra,double totalValorActual) {

        this.totalPlaymobil = totalPlaymobil;
        this.totalCompra = totalCompra;
        this.totalValorActual = totalValorActual;
    }

    public int getTotalPlaymobil() {
        return totalPlaymobil;
    }

    public void setTotalPlaymobil(int totalPlaymobil) {
        this.totalPlaymobil = totalPlaymobil;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public double getTotalValorActual() {
        return totalValorActual;
    }

    public void setTotalValorActual(double totalValorActual) {
        this.totalValorActual = totalValorActual;
    }

    public double getBeneficio() {
        return totalValorActual - totalCompra;
    }
}