package main.java.com.upb.agripos;

public class MainExceptionDemo {

    public static void main(String[] args) {

        System.out.println(
            "Hello, I am Ridho Kurniawan - 240202881 (Week 9)"
        );

        ShoppingCart cart = new ShoppingCart();

        
        try {
            Product p1 = new Product(
                "P01", "Pupuk Organik", -25000, 5
            );
            cart.addProduct(p1, 2);

        } catch (InvalidPriceException e) {
            System.out.println("Error Harga: " + e.getMessage());
        } catch (InvalidQuantityException e) {
            System.out.println("Error Quantity: " + e.getMessage());
        } finally {
            System.out.println("Validasi produk selesai\n");
        }
        
        
        try {
            Product p2 = new Product(
                "P02", "Bibit Padi", 15000, 10
            );
            cart.removeProduct(p2);

        } catch (InvalidPriceException e) {
            System.out.println("Error Harga: " + e.getMessage());
        } catch (ProductNotFoundException e) {
            System.out.println("Error Keranjang: " + e.getMessage());
        }

    
        try {
            Product p3 = new Product(
                "P03", "Pestisida", 30000, 2
            );
            cart.addProduct(p3, 5);
            cart.checkout();

        } catch (InvalidPriceException e) {
            System.out.println("Error Harga: " + e.getMessage());
        } catch (InvalidQuantityException e) {
            System.out.println("Error Quantity: " + e.getMessage());
        } catch (EmptyStockException e) {
            System.out.println("Error Keranjang: " + e.getMessage());
        } catch (InsufficientStockException e) {
            System.out.println("Error Stok: " + e.getMessage());
        } finally {
            System.out.println("Proses checkout selesai");
        }
    }
}
