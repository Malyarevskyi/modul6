package dz6_2;


import jdk.nashorn.internal.objects.annotations.Function;

import java.util.*;


abstract class MusicalInstrument {
    public abstract String getType();

    @Override
    public String toString() {
        return "MusicalInstrument{"+getType()+"}";
    }
}

class Piano extends MusicalInstrument {

    @Override
    public String getType() {
        return "piano";
    }
}

class Guitar extends MusicalInstrument {

    @Override
    public String getType() {
        return "guitar";
    }
}

class Trumpet extends MusicalInstrument {

    @Override
    public String getType() {
        return "trumpet";
    }
}


class MusicShop {
    List<MusicalInstrument> musicalInstruments;

    public List<MusicalInstrument> getMusicalInstruments() {
        return musicalInstruments;
    }

    public void setMusicalInstruments(List<MusicalInstrument> musicalInstruments) {
        this.musicalInstruments = musicalInstruments;
    }

    @Override
    public String toString() {
        return "MusicShop{" +
                "musicalInstruments=" + musicalInstruments +
                '}';
    }
}

public class dz6_2 {

    public static void main(String[] args) {
        MusicShop shop = new MusicShop();


        ArrayList<MusicalInstrument> musicalInstruments = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            musicalInstruments.add(new Piano());
        }
        for (int i = 0; i < 16; i++) {
            musicalInstruments.add(new Guitar());
        }
        for (int i = 0; i < 7; i++) {
            musicalInstruments.add(new Trumpet());
        }
        shop.setMusicalInstruments(musicalInstruments);


        Map<String, Integer> order = new HashMap<>();
        order.put("guitar", 7);
        order.put("trumpet", 2);

        System.out.println(shop);

        try {
            List<MusicalInstrument> musicInstrumentToBeRemoved = prepareListOfMusicInstrumentToRemove(shop, order);
            removeInstrumentFromTheShop(shop, order);
            System.out.println("Order1: "+musicInstrumentToBeRemoved);
        } catch (Exception e) {
            System.out.println("Exception happened with order#1: "+e.getMessage());
        }

        System.out.println(shop);

        Map<String, Integer> order2 = new HashMap<>();
        order2.put("piano", 1);

        try {
            List<MusicalInstrument> musicInstrumentToBeRemoved = prepareListOfMusicInstrumentToRemove(shop, order2);
            removeInstrumentFromTheShop(shop, order2);
            System.out.println("Order2: "+musicInstrumentToBeRemoved);
        } catch (Exception e) {
            System.out.println("Exception happened with order#2: "+e.getMessage());
        }

        System.out.println(shop);

        Map<String, Integer> order3 = new HashMap<>();
        order3.put("piano", 1);
        order3.put("guitar", 8);
        order3.put("trumpet", 6);

        try {
            List<MusicalInstrument> musicInstrumentToBeRemoved = prepareListOfMusicInstrumentToRemove(shop, order3);
            removeInstrumentFromTheShop(shop, order3);
            System.out.println("Order#3: "+musicInstrumentToBeRemoved);
        } catch (Exception e) {
            System.out.println("Exception happened with order#3: "+e.getMessage());
        }
        System.out.println(shop);
    }

    private static List<MusicalInstrument> prepareListOfMusicInstrumentToRemove(MusicShop musicShop, Map<String, Integer> order) {
        List<MusicalInstrument> result = new ArrayList<>();

        for (Map.Entry<String, Integer> orderEntry : order.entrySet()) {
            String instrumentType = orderEntry.getKey();
            Integer numberOfInstrumentToBeRemoved = orderEntry.getValue();
            int numberOfInstrumentsRemoved = 0;
            for (MusicalInstrument musicalInstrument : musicShop.getMusicalInstruments()) {
                if (musicalInstrument.getType().equals(instrumentType) && numberOfInstrumentsRemoved<numberOfInstrumentToBeRemoved) {
                    result.add(musicalInstrument);
                    numberOfInstrumentsRemoved++;
                }
            }
            if(numberOfInstrumentsRemoved<numberOfInstrumentToBeRemoved)
                throw new IllegalArgumentException("Shop does not have enough " + instrumentType+"s");
        }

        return result;
    }

    private static void removeInstrumentFromTheShop(MusicShop musicShop, Map<String, Integer> order){
        for (Map.Entry<String, Integer> orderEntry : order.entrySet()) {
            String animalType = orderEntry.getKey();
            Integer numberOfAnimalToBeRemoved = orderEntry.getValue();
            int numberOfInstrumentsRemoved = 0;
            Iterator<MusicalInstrument> iterator = musicShop.getMusicalInstruments().iterator();
            while (iterator.hasNext()) {
                MusicalInstrument musicalInstrument = iterator.next();
                if (musicalInstrument.getType().equals(animalType) && numberOfInstrumentsRemoved<numberOfAnimalToBeRemoved) {
                    iterator.remove();
                    numberOfInstrumentsRemoved++;
                }
            }
        }
    }

}