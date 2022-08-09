package au.edu.sydney.soft3202.task1;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingBasketTest {
    private ShoppingBasket s;

    @BeforeEach
    void setup(){
        s = new ShoppingBasket();
    }

    @Test
    void testCorrectAddItem(){
        s.addItem("apple", 1);
        assertEquals(2.5,s.getValue());
    }

    @Test
    void testCorrectAddItem2(){
        s.addItem("pear", 1);
        s.addItem("pEAR", 1);
        assertEquals(6.0,s.getValue());
    }
    @Test
    void testCorrectAddItem3(){
        s.addItem("apple",1000000000);
        assertEquals(2500000000.0,s.getValue());
    }

    @Test
    void testCorrectAddItem4(){
        s.addItem("ApPle",1);
        assertEquals(2.5,s.getValue());
    }

    @Test
    void testIncorrectAddItem(){
        assertThrows(IllegalArgumentException.class,() -> s.addItem("cake",1));
    }

    @Test
    void testIncorrectAddItem2(){
        assertThrows(IllegalArgumentException.class,() -> s.addItem("apple",-1));
    }

    @Test
    void testIncorrectAddItem3(){
        assertThrows(IllegalArgumentException.class,() -> s.addItem("apple",0));
    }

    @Test
    void testIncorrectAddItem4(){
        assertThrows(IllegalArgumentException.class,() -> s.addItem("choc",0));
    }

    @Test
    void testIncorrectAddItem5(){
        assertThrows(IllegalArgumentException.class,() -> s.addItem(null,1));
    }

    @Test
    void testIncorrectAddItem6(){
        assertThrows(IllegalArgumentException.class,() -> s.addItem(null,-1));
    }


    @Test
    void testCorrectGetItem(){
        List<javafx.util.Pair<String,Integer>> listCopy = s.getItems();

        int checkedCount = 0;
        for(int i = 0;i<listCopy.size();i++){
            checkedCount+=listCopy.get(i).getValue();
        }

        assertEquals(0,checkedCount);
    }
    @Test
    void testCorrectGetItem2(){
        s.addItem("apple", 1);
        s.addItem("orange", 2);
        s.addItem("pear", 3);

        List<javafx.util.Pair<String,Integer>> listCopy = s.getItems();

        int checkedCount = 0;
        int appleCount = 0;
        for(int i = 0;i<listCopy.size();i++){
            if(listCopy.get(i).getKey().equals("apple")){
                appleCount+=listCopy.get(i).getValue();
            }
            checkedCount+=listCopy.get(i).getValue();
        }

        assertEquals(6, checkedCount);
        assertEquals(1, appleCount);

    }

    @Test
    void testCorrectGetItem3(){
        s.addItem("apple", 3);
        s.addItem("orange", 2);
        s.addItem("banana", 1);

        List<javafx.util.Pair<String,Integer>> listCopy = s.getItems();

        int checkedCount = 0;
        int appleCount = 0;
        for(int i = 0;i<listCopy.size();i++){
            if(listCopy.get(i).getKey().equals("apple")){
                appleCount+=listCopy.get(i).getValue();
            }
            checkedCount+=listCopy.get(i).getValue();
        }

        assertEquals(6, checkedCount);
        assertEquals(3, appleCount);

        s.removeItem("apple", 3);

        listCopy = s.getItems();

        int checkedCount2 = 0;
        int appleCount2 = 0;
        for(int i = 0;i<listCopy.size();i++){
            if(listCopy.get(i).getKey().equals("apple")){
                appleCount2+=listCopy.get(i).getValue();
            }
            checkedCount2+=listCopy.get(i).getValue();
        }

        assertEquals(3, checkedCount2);
        assertEquals(0, appleCount2);


    }

    @Test
    void testCorrectRemoveItem(){
        s.addItem("apple", 4);
        assertTrue(s.removeItem("apple",1));
        assertEquals(7.5,s.getValue());

    }

    @Test
    void testCorrectRemoveItem2(){
        s.addItem("orange", 4);
        assertTrue(s.removeItem("ORaNgE",1));
        assertEquals(3.75,s.getValue());
    }

    @Test
    void testCorrectRemoveItem3(){
        s.addItem("pear", 4);
        assertFalse(s.removeItem("cake", 1));
    }

    @Test
    void testCorrectRemoveItem4(){
        s.addItem("banana", 4);
        assertFalse(s.removeItem("banana", 40));
    }
    @Test
    void testCorrectRemoveItem5(){
        s.addItem("apple", 4);
        assertTrue(s.removeItem("apple", 4));
    }

    @Test
    void testIncorrectRemoveItem(){
        s.addItem("pear", 4);
        assertFalse(s.removeItem(null,1));

    }
    @Test
    void testIncorrectRemoveItem2(){
        s.addItem("orange", 4);
        assertThrows(IllegalArgumentException.class,() -> s.removeItem("orange",0));

    }
    @Test
    void testIncorrectRemoveItem3(){
        s.addItem("orange", 4);
        assertThrows(IllegalArgumentException.class,() -> s.removeItem(null,0));

    }
    @Test
    void testIncorrectRemoveItem4(){
        s.addItem("orange", 4);
        assertThrows(IllegalArgumentException.class,() -> s.removeItem("grape",0));

    }

    @Test
    void testGetValueEmpty(){
        assertNull(s.getValue());
    }

    @Test
    void testGetValueModified(){
        s.addItem("ORANge", 3);
        s.removeItem("orange", 2);
        assertEquals(1.25, s.getValue());
    }

    @Test
    void testCorrectClear(){
        s.addItem("orange", 4);
        s.clear();
        assertNull(s.getValue());

    }
    @Test
    void testCorrectClear2(){
        s.clear();
        assertEquals(0,s.getItems().size());

    }



}
