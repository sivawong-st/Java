package OOSE_H2;

public class Person {
    private String cardId;
    private String name;
    private String gender;

    public Person(String cardId, String name, String gender) {
        if (cardId.length() != 13) {
            throw new IllegalArgumentException("Account ID must be 13 digits");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("AccountName <= 50 alphabet !!!");
        }
        this.cardId = cardId;
        this.name = name;
        this.gender = gender;
    }

    public String getCardId() {
        return cardId;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public void displayDetails() {
        System.out.println("Card ID: " + cardId);
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
    }
}
