package se.ferander.digg;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

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
