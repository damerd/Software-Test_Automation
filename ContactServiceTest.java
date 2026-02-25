import org.junit.Test;

import static org.junit.Assert.*;

public class ContactServiceTest {

    @Test
    public void addsContactWithUniqueId() {
        ContactService service = new ContactService();
        Contact c = new Contact("1", "A", "B", "1234567890", "Addr");

        service.addContact(c);
        assertNotNull(service.getContact("1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsDuplicateId() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "A", "B", "1234567890", "Addr"));
        service.addContact(new Contact("1", "C", "D", "0987654321", "Addr2"));
    }

    @Test
    public void deletesById() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "A", "B", "1234567890", "Addr"));

        service.deleteContact("1");
        assertNull(service.getContact("1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteRejectsMissingId() {
        ContactService service = new ContactService();
        service.deleteContact("nope");
    }

    @Test
    public void updatesFieldsById() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "A", "B", "1234567890", "Addr"));

        service.updateFirstName("1", "John");
        service.updateLastName("1", "Smith");
        service.updatePhone("1", "0987654321");
        service.updateAddress("1", "New Address");

        Contact updated = service.getContact("1");
        assertEquals("John", updated.getFirstName());
        assertEquals("Smith", updated.getLastName());
        assertEquals("0987654321", updated.getPhone());
        assertEquals("New Address", updated.getAddress());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateRejectsUnknownId() {
        ContactService service = new ContactService();
        service.updatePhone("x", "1234567890");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addRejectsNullContact() {
        ContactService service = new ContactService();
        service.addContact(null);
    }
}