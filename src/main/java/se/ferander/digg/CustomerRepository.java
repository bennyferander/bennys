package se.ferander.digg;

import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ApplicationScoped
public class CustomerRepository {
    private static final Logger LOG = Logger.getLogger(CustomerRepository.class);

    private final CopyOnWriteArrayList<Customer> customers = new CopyOnWriteArrayList<>();

    public List<Customer> list() {
        return customers;
    }

    public void add(Customer c) {
        customers.add(c);
    }

    public void clear() {
        customers.clear();
    }

    public int size() {
        return customers.size();
    }

    public void seedCustomers() {
        if(list().isEmpty()){
            List<Customer> customers = Arrays.asList(
                    new Customer("Anna Andersson", "Storgatan 1, Stockholm", "anna.andersson@example.com", "0701111111"),
                    new Customer("Björn Berg", "Kyrkogatan 2, Göteborg", "bjorn.berg@example.com", "0702222222"),
                    new Customer("Cecilia Carlsson", "Västra Vägen 3, Malmö", "cecilia.carlsson@example.com", "0703333333"),
                    new Customer("David Dahl", "Östra Gatan 4, Uppsala", "david.dahl@example.com", "0704444444"),
                    new Customer("Emma Eriksson", "Norra Torg 5, Lund", "emma.eriksson@example.com", "0705555555"),
                    new Customer("Fredrik Fredriksson", "Södra Allén 6, Västerås", "fredrik.fredriksson@example.com", "0706666666"),
                    new Customer("Gabriella Gustafsson", "Stora Nygatan 7, Örebro", "gabriella.gustafsson@example.com", "0707777777"),
                    new Customer("Henrik Holm", "Lilla Gatan 8, Linköping", "henrik.holm@example.com", "0708888888"),
                    new Customer("Isabella Isaksson", "Kungsgatan 9, Helsingborg", "isabella.isaksson@example.com", "0709999999"),
                    new Customer("Johan Johansson", "Drottninggatan 10, Norrköping", "johan.johansson@example.com", "0701010101"),
                    new Customer("Karin Karlsson", "Kungsbacka 11, Karlstad", "karin.karlsson@example.com", "0701111212"),
                    new Customer("Lars Larsson", "Södra Vägen 12, Sundsvall", "lars.larsson@example.com", "0701313131"),
                    new Customer("Maria Magnusson", "Västra Vägen 13, Borås", "maria.magnusson@example.com", "0701414141"),
                    new Customer("Niklas Nilsson", "Norra Vägen 14, Gävle", "niklas.nilsson@example.com", "0701515151"),
                    new Customer("Oskar Olsson", "Storgatan 15, Jönköping", "oskar.olsson@example.com", "0701616161"),
                    new Customer("Petra Persson", "Kungsgatan 16, Eskilstuna", "petra.persson@example.com", "0701717171"),
                    new Customer("Rickard Rydberg", "Lilla Nygatan 17, Karlskrona", "rickard.rydberg@example.com", "0701818181"),
                    new Customer("Sara Svensson", "Stora Torget 18, Halmstad", "sara.svensson@example.com", "0701919191"),
                    new Customer("Thomas Thomasson", "Södra Torg 19, Umeå", "thomas.thomasson@example.com", "0702020202"),
                    new Customer("Ulrika Ulfsson", "Norra Allén 20, Sundbyberg", "ulrika.ulfsson@example.com", "0702121212"),
                    new Customer("Viktor Vikström", "Östra Vägen 21, Luleå", "viktor.vikstrom@example.com", "0702222323"),
                    new Customer("Yvonne Yngvesson", "Storgatan 22, Karlskoga", "yvonne.yngvesson@example.com", "0702323232"),
                    new Customer("Zlatan Zetterlund", "Kungsgatan 23, Borlänge", "zlatan.zetterlund@example.com", "0702424242"),
                    new Customer("Åsa Åkesson", "Västra Allén 24, Östersund", "asa.akesson@example.com", "0702525252"),
                    new Customer("Örjan Öberg", "Norra Gatan 25, Falun", "orjan.oberg@example.com", "0702626262")
            );

            list().addAll(customers);

            LOG.info("25 kunder skapade i minnet");
        }
    }
}
