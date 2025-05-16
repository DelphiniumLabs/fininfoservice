package com.goit.fininfoservice.utils;

public class Constants {

    private Constants(){}
    public static final String GREETING = """
             
            <b>Hello! I am The Financial Information Bot.</b>
            <i>I will keep you aware of changing currency exchange rates.</i>
            Visit our site <a href='https://finviz.com'> Finviz</a>
            """;
    public static final String UNKNOWN_COMMAND_TEXT = """
            Oops! Unknown command entered!"
            """;
    public static final String HELP_TEXT= """
            Please read instruction before using this amazing bot!
            This is list of commands you can use in this bot:
            /start - starts bot
            /stop - stops bot
            """;
    public static final String STOP_TEXT = """ 
            Financial bot stopped. Looking forward for you again!
            Start bot with /start command.
            """;
}
