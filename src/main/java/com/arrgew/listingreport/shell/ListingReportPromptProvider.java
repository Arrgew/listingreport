package com.arrgew.listingreport.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class ListingReportPromptProvider implements PromptProvider{

    @Override
    public AttributedString getPrompt(){
        return new AttributedString("Listings-Report:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    }
}
