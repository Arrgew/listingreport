package com.arrgew.listingreport.command;

import com.arrgew.listingreport.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class CustomCommands {
    @Autowired
    private MarketplaceService marketplaceService;
    @Autowired
    private ListingStatusService listingStatusService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private ListingService listingService;
    @Autowired
    private ReportingService reportingService;

    @ShellMethod("Gets Data from the api, validates it and saves it to the local database")
    public void load(){
        marketplaceService.loadMarketplaces();
        listingStatusService.loadListingStatuses();
        locationService.loadLocations();
        listingService.loadListings();
    }

    @ShellMethod("Creates a report in JSON and uploads it to the FTP server")
    public void createReport(){
        reportingService.createReport(listingService);
    }

}
