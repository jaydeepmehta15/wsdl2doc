package de.sattelmair.wsdl2doc;

import de.sattelmair.wsdl2doc.service.ApplicationMainProcessorService;
import de.sattelmair.wsdl2doc.service.impl.ApplicationMainProcessorServiceImpl;

public class Application {

    public static void main(final String[] args) {
        final ApplicationMainProcessorService applicationMainProcessorService = new ApplicationMainProcessorServiceImpl();
        applicationMainProcessorService.process(args);
    }

}
