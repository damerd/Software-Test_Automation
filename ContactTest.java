import org.junit.Test;

import static org.junit.Assert.*;

public class ContactTest {

    @Test
    public void createsValidContact() {
        Contact c = new Contact("123", "Damien", "Dennis", "1234567890", "123 Main Street");
        assertEquals("123", c.getContactId());
        assertEquals("Damien", c.getFirstName());
        assertEquals("Dennis", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main Street", c.getAddress());
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNullContactId() {
        new Contact(null, "A", "B", "1234567890", "Addr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsLongContactId() {
        new Contact("12345678901", "A", "B", "1234567890", "Addr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNullFirstName() {
        new Contact("1", null, "B", "1234567890", "Addr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsLongFirstName() {
        new Contact("1", "12345678901", "B", "1234567890", "Addr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNullLastName() {
        new Contact("1", "A", null, "1234567890", "Addr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsLongLastName() {
        new Contact("1", "A", "12345678901", "1234567890", "Addr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNullPhone() {
        new Contact("1", "A", "B", null, "Addr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsPhoneNotTenDigits() {
        new Contact("1", "A", "B", "123", "Addr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsPhoneWithNonDigits() {
        new Contact("1", "A", "B", "12345abc90", "Addr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNullAddress() {
        new Contact("1", "A", "B", "1234567890", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsLongAddress() {
        String longAddr = "1234567890123456789012345678901";
        new Contact("1", "A", "B", "1234567890", longAddr);
    }

    @Test
    public void settersRejectInvalidValues() {
        Contact c = new Contact("1", "A", "B", "1234567890", "Addr");

        try {
            c.setFirstName(null);
            fail("Expected IllegalArgumentException for null firstName");
        } catch (IllegalArgumentException ignored) { }

        try {
            c.setLastName(null);
            fail("Expected IllegalArgumentException for null lastName");
        } catch (IllegalArgumentException ignored) { }

        try {
            c.setPhone("123");
            fail("Expected IllegalArgumentException for invalid phone");
        } catch (IllegalArgumentException ignored) { }

        try {
            c.setAddress(null);
            fail("Expected IllegalArgumentException for null address");
        } catch (IllegalArgumentException ignored) { }
    }

    @Test
    public void validSettersWork() {
        Contact c = new Contact("1", "A", "B", "1234567890", "Addr");
        c.setFirstName("John");
        c.setLastName("Smith");
        c.setPhone("0987654321");
        c.setAddress("New Address");
        assertEquals("John", c.getFirstName());
        assertEquals("Smith", c.getLastName());
        assertEquals("0987654321", c.getPhone());
        assertEquals("New Address", c.getAddress());
    }

    @Test
    public void equalsAndHashCodeWorkForSameId() {
        Contact c1 = new Contact("1", "A", "B", "1234567890", "Addr");
        Contact c2 = new Contact("1", "A", "B", "1234567890", "Addr");
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    public void equalsReturnsTrueForSameObject() {
        Contact c = new Contact("1", "A", "B", "1234567890", "Addr");
        assertTrue(c.equals(c));
    }

    @Test
    public void equalsReturnsFalseForNull() {
        Contact c = new Contact("1", "A", "B", "1234567890", "Addr");
        assertFalse(c.equals(null));
    }

    @Test
    public void equalsReturnsFalseForDifferentType() {
        Contact c = new Contact("1", "A", "B", "1234567890", "Addr");
        assertFalse(c.equals("not a contact"));
    }

    @Test
    public void equalsReturnsFalseForDifferentId() {
        Contact c1 = new Contact("1", "A", "B", "1234567890", "Addr");
        Contact c2 = new Contact("2", "A", "B", "1234567890", "Addr");
        assertFalse(c1.equals(c2));
    }
}