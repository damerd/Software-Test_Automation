import java.util.Objects;

public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        this.contactId = validateId(contactId);
        this.firstName = validateName(firstName, "firstName");
        this.lastName = validateName(lastName, "lastName");
        this.phone = validatePhone(phone);
        this.address = validateAddress(address);
    }

    private String validateId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("contactId cannot be null");
        }
        if (id.length() > 10) {
            throw new IllegalArgumentException("contactId cannot be longer than 10");
        }
        return id;
    }

    private String validateName(String value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
        if (value.length() > 10) {
            throw new IllegalArgumentException(fieldName + " cannot be longer than 10");
        }
        return value;
    }

    private String validatePhone(String value) {
        if (value == null) {
            throw new IllegalArgumentException("phone cannot be null");
        }
        if (value.length() != 10) {
            throw new IllegalArgumentException("phone must be exactly 10 digits");
        }
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c < '0' || c > '9') {
                throw new IllegalArgumentException("phone must be exactly 10 digits");
            }
        }
        return value;
    }

    private String validateAddress(String value) {
        if (value == null) {
            throw new IllegalArgumentException("address cannot be null");
        }
        if (value.length() > 30) {
            throw new IllegalArgumentException("address cannot be longer than 30");
        }
        return value;
    }

    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = validateName(firstName, "firstName");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = validateName(lastName, "lastName");
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = validatePhone(phone);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = validateAddress(address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(contactId, contact.contactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId);
    }
}