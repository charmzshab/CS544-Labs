package repositories;

import domain.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CDRepository extends JpaRepository<CD,Long>, JpaSpecificationExecutor<CD> {
    List<CD> findByArtistAndPriceLessThan(String artist, double price);

    @Query(name = "CD.findByArtist")
    List<CD> findByArtist(@Param("artist") String artist);

    @Query("SELECT c FROM CD c WHERE c.artist = :artist AND c.price > :amount")
    List<CD> findCDsByArtistAndMinPrice(@Param("artist") String artist, @Param("amount") double amount);

    @Query(value = "SELECT * FROM Product p JOIN CD c ON p.productNumber = c.productNumber WHERE c.artist = 'U2'", nativeQuery = true)
    List<CD> findCDsFromU2();
}
