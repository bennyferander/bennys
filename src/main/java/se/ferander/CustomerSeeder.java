package se.ferander;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class CustomerSeeder {
    private static final Logger LOG = Logger.getLogger(CustomerSeeder.class);

    @Inject
    CustomerRepository customerRepository;

    void onStart(@Observes StartupEvent ev) {
        if(customerRepository.size() == 0){
            customerRepository.seedCustomers();
        }
    }
}
