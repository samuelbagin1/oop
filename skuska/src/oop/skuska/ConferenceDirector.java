package oop.skuska;

public class ConferenceDirector {
    private final EmailBuilderInterface builder;
    private final String senderAddress;
    private final String senderName;

    // v komentaroch su popisane
    //  - parametre konstruktora a metod
    //  - texty, aby ste ich nemuseli prepisovat rucne (pre zostavenie mozete pouzit funkciu String.format)

    // constructor(builder, adresa odosielatela, meno odosielatela) {
    // }

    // pozvanka na konferenciu
    // void invite(adresa prijimatela, meno prijimatela, nazov konferencie, miesto konania, datum konania, cas konania) {

        // predmet spravy
//         "%s (invitation)"
//
//         obsah emailu
//         """
//         Dear %s,
//         We are pleased to invite you to our upcoming conference, %s.
//         The conference is scheduled to take place on %s, at %s, in %s.
//         Sincerely,
//         %s
//             """

    // }

    public ConferenceDirector(EmailBuilderInterface builder, String senderAddress, String senderName) {
        this.builder = builder;
        this.senderAddress = senderAddress;
        this.senderName = senderName;
    }

    public void invite (String receiverAddress, String receiverName, String conferenceName, String location, String date, String time) {
        // predmet spravy

        builder.setSender(senderAddress);
        builder.setReceiver(receiverAddress);

        //String subject = String.format("%s (invitation)", conferenceName);
        builder.setSubject(String.format("%s (invitation)", conferenceName));

        // obsah emailu
        String content = String.format(
        """
        Dear %s,
        We are pleased to invite you to our upcoming conference, %s.
        The conference is scheduled to take place on %s, at %s, in %s.
        Sincerely,
        %s
        """,
                receiverName, conferenceName, date, time, location, senderName);


        builder.setContent(content);
    }

    // potvrdenie rezervacie
    // void confirm(adresa prijimatela, meno prijimatela, nazov konferencie) {

        // predmet spravy
        // "%s (registration confirmation)"

        // obsah emailu
//         """
//         Dear %s,
//         We are delighted to confirm your registration for the %s conference.
//         Singecerly,
//         %s
//         """

    // }

    public void confirm(String receiverAddress, String receiverName, String conferenceName) {
        builder.setSender(senderAddress);
        builder.setReceiver(receiverAddress);

        builder.setSubject(String.format("%s (registration confirmation)", conferenceName));

        // obsah emailu
        String content = String.format(
                """
                Dear %s,
                We are delighted to confirm your registration for the %s conference.
                Singecerly,
                %s
                """,
                receiverName, conferenceName, senderName);


        builder.setContent(content);
    }


}




//package oop.skuska;

//public class ConferenceDirector {
//    private EmailBuilderInterface builder;
//    private String senderAddress;
//    private String senderName;
//
//    // v komentaroch su popisane
//    //  - parametre konstruktora a metod
//    //  - texty, aby ste ich nemuseli prepisovat rucne (pre zostavenie mozete pouzit funkciu String.format)
//
//    // constructor(builder, adresa odosielatela, meno odosielatela) {
//    // }
//
//    // pozvanka na konferenciu
//    // void invite(adresa prijimatela, meno prijimatela, nazov konferencie, miesto konania, datum konania, cas konania) {
//
//    // predmet spravy
//    // "%s (invitation)"
//
//    // obsah emailu
//    // """
//    // Dear %s,
//    // We are pleased to invite you to our upcoming conference, %s.
//    // The conference is scheduled to take place on %s, at %s, in %s.
//    // Sincerely,
//    // %s
//    //     """
//
//    // }
//
//    public ConferenceDirector(EmailBuilderInterface builder, String senderAddress, String senderName) {
//        this.builder = builder;
//        this.senderAddress = senderAddress;
//        this.senderName = senderName;
//    }
//
//    public void invite (String receiverAddress, String receiverName, String conferenceName, String location, String date, String time) {
//        // predmet spravy
//        String subject = String.format("%s (invitation)", conferenceName);
//
//        // obsah emailu
//        String content = String.format(
//                "Dear %s,\n" +
//                        "We are pleased to invite you to our upcoming conference, %s.\n" +
//                        "The conference is scheduled to take place on %s, at %s, in %s.\n" +
//                        "Sincerely,\n" +
//                        "%s",
//                receiverName, conferenceName, date, location, time
//        );
//
//        // vytvorenie emailu pomocou buildera
//        EmailBuilder builder = new EmailBuilder();
//        builder.setSender(senderName);
//    }
//
//    // potvrdenie rezervacie
//    // void confirm(adresa prijimatela, meno prijimatela, nazov konferencie) {
//
//    // predmet spravy
//    // "%s (registration confirmation)"
//
//    // obsah emailu
//    // """
//    // Dear %s,
//    // We are delighted to confirm your registration for the %s conference.
//    // Singecerly,
//    // %s
//    // """
//
//    // }
//
//    public void confirm(String receiverAddress, String receiverName, String conferenceName) {
//        // predmet spravy
//        String subject = String.format("%s (registration confirmation)", conferenceName);
//
//        // obsah emailu
//        String content = String.format(
//                "Dear %s,\n" +
//                        "We are delighted to confirm your registration for the %s conference.\n" +
//                        "Sincerely,\n" +
//                        "%s",
//                receiverName, conferenceName, receiverName
//        );
//
//        // vytvorenie emailu pomocou buildera
//        EmailBuilder builder = new EmailBuilder();
//        builder.setSender(senderName);
//    }
//
//
//}

