package api.businessController;

import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.entities.Theme;
import api.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThemeBusinessControllerIT {

    private ThemeBusinessController themeBusinessController;
    private Theme theme;

    @BeforeEach
    void prepare() {
        DaoFactory.setFactory(new DaoMemoryFactory());
        this.themeBusinessController = new ThemeBusinessController();
        User user = new User("nick", null);
        DaoFactory.getFactory().getUserDao().save(user);
        this.theme = new Theme("uno");
        DaoFactory.getFactory().themeDao().save(this.theme);
    }

    @Test
    void testReadAverageWithoutVote() {
        assertTrue(Double.isNaN(this.themeBusinessController.readAverage(this.theme.getId())));
    }

    @Test
    void testReadAverage() {
        this.themeBusinessController.createVote(theme.getId(), 2);
        this.themeBusinessController.createVote(theme.getId(), 4);
        this.themeBusinessController.createVote(theme.getId(), 6);
        assertEquals(4.0, this.themeBusinessController.readAverage(theme.getId()), 10e-5);
    }

    @Test
    void testFindByAverageGreaterThanEqualWithoutVote() {
        assertTrue(this.themeBusinessController.findByAverageGreaterThanEqual(5.0).isEmpty());
    }

    @Test
    void testFindByAverageGreaterThanEqualEmptyList() {
        this.themeBusinessController.createVote(theme.getId(), 2);
        assertTrue(this.themeBusinessController.findByAverageGreaterThanEqual(5.0).isEmpty());
    }

    @Test
    void testFindByAverageGreaterThanEqual() {
        this.themeBusinessController.createVote(theme.getId(), 4);
        this.themeBusinessController.createVote(theme.getId(), 6);
        assertFalse(this.themeBusinessController.findByAverageGreaterThanEqual(5.0).isEmpty());
    }

}
