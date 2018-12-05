/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.calculators;

import FunctionLayer.BillOfMaterial;
import FunctionLayer.Carport;
import FunctionLayer.LineItem;
import FunctionLayer.Material;
import FunctionLayer.exceptions.CalculatorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author leage
 */
public class LineItemQtyGenerator {

    double toMilimeters = 1000;
    LineItemTypeOfMaterialGenerator calc = new LineItemTypeOfMaterialGenerator();

    public BillOfMaterial makeBillOfMaterial(Carport carport, HashMap<Integer, TreeMap<Double, Material>> boards) throws CalculatorException {
        List<LineItem> lineItemList = new ArrayList();
        BillOfMaterial billOfMaterial = new BillOfMaterial();

        Material board;

        //Spær
        board = calc.getBoardForRafter(carport, boards.get(10));
        lineItemList.add(new LineItem(getQTYForRafter(carport, board), "Spær", board));

        //Rem
        board = calc.getBoardForRem(carport, boards.get(6));
        lineItemList.add(new LineItem(getQTYForRem(carport, board), "Rem", board));

        //Understern til siderne
        board = calc.getBoardLengthForOversternAndUndersternSides(carport, boards.get(6));
        lineItemList.add(new LineItem(getQTYForRem(carport, board), "Understern til siderne", board));

        //Understern til for og bagende
        board = calc.getBoardForUndersternFrontAndBack(carport, boards.get(6));
        lineItemList.add(new LineItem(getQTYForRafter(carport, board), "Understern til for og bagende", board));
//            //Overstern til siderne
//            board = getBoardLengthForOversternAndUndersternSides(carport, boards.get(6));
//            billOfMaterial.addMaterialToBOM(new LineItem(calculateQtyOfRem(carport, board), "Overstern til siderne", board));
//
//            //Overstern til for og bagende
//            board = getBoardLengthForOversternAndUndersternSides(carport, boards.get(6));
//            billOfMaterial.addMaterialToBOM(new LineItem(calculateQtyOfRaftersForRoofAndUniversalBracketsForRafter(carport), "Overstern til for og bagende", board));
//
        //Universalbeslag højre
        board = boards.get(12).firstEntry().getValue();
        lineItemList.add(new LineItem(getUniversalBracketsQty(carport, board), "Universalbeslag højre", board));

        //Universalbeslag venstre
        board = boards.get(13).firstEntry().getValue();
        lineItemList.add(new LineItem(getUniversalBracketsQty(carport, board), "Universalbeslag venstre", board));
      
        //Stolper
        board = boards.get(7).firstEntry().getValue();
        lineItemList.add(new LineItem(getQtyOfPosts(carport), "Stolper", board));
        
        //Bræddebolt
        board = boards.get(14).firstEntry().getValue();
        lineItemList.add(new LineItem(calculateQtyOfBræddebolt(carport, board), "Bræddebolt", board));

//            //Skruer
//            billOfMaterial.addMaterialToBOM(new LineItem(0, "Skruer", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6))));

        billOfMaterial.setLineItems(lineItemList);

        return billOfMaterial;
    }


    public int getQTYForRafter(Carport carport, Material board) throws CalculatorException {

        try {
            if (carport.getRoof().getRoofSlopeCelsius() == 0) {
                double spaceBetweenEachRafter = 0.55;
                double rafterWidth = 0.02;
                double totalRafterDimension = spaceBetweenEachRafter + rafterWidth; // Total dimension for each rafter including both space and material.
                double rafters = Math.ceil(carport.getLength() / totalRafterDimension); // Total amount of rafter based on calculating length with total dimension per rafter
                if (carport.getWidth() * toMilimeters > board.getLength()) {
                    return (int) rafters * 2;                    
                }
                return (int) rafters;
            } else {
                return 8;
            }
        } catch (Exception ex) {
            throw new CalculatorException("There was an error calculating the amount of rafters for the carport.", ex);
        }
    }

 
    public int getQTYForRem(Carport carport, Material board) throws CalculatorException {
        try {
        int amountOfRem = 2;
            if (board.getLength() < carport.getLength() * toMilimeters) {
                return amountOfRem * 2;
            }
            return amountOfRem;            
        } catch (Exception ex) {
            throw new CalculatorException("There was an error counting rem's for the carport.", ex);
        }
    }


    public int getQtyOfPosts(Carport carport) throws CalculatorException {
        try {
        int meterPerPost = 2;
            double posts = ((Math.ceil(carport.getLength() / meterPerPost) * 2));
            return (int) posts;
        } catch (Exception ex) {
            throw new CalculatorException("There was an error calculating the amount of posts for the carport", ex);
        }
    }

    public int countEaves(Carport carport) throws CalculatorException {
        //carport.getRoof().calculateRoofDimensions(carport);
        try {
        double eavesWidth = 1.0;
            double countEaves = Math.ceil(carport.getRoof().getWidth() / eavesWidth); // Total pieces of eaves with 1 meter width. 
            return (int) countEaves;
        } catch (Exception ex) {
            throw new CalculatorException("There was an error calculating the amount of eaves for the roof.", ex);
        }
    }

    //stykslisten says 21 rygsten for 7,3 m, we calculate with 3 rygsten each meter
    public int calculateRygstensTiles(Carport carport) throws CalculatorException {
        try {
        int rygstenTilesPrMeterLength = 3;
            double roofTiles = Math.ceil((carport.getRoof().getLength())) * rygstenTilesPrMeterLength;
            return (int) roofTiles;
        } catch (Exception ex) {
            throw new CalculatorException("There was an error calculating the amount of tiles for the roof.", ex);
        }
    }

    public Material returnMaterialForFarciaAndRainware(Carport carport, TreeMap<Double, Material> boards) throws CalculatorException {
        try {
        double boardLengthHypotenuse = calculateBoardLengthForFarciaAndRainware(carport); // Kalder metode for at få længden på vinskederne (hypotenusen)
            return calc.boardCalculator(boardLengthHypotenuse, carport, boards);  // Kalder boardCalculator som finder det bræt som matcher længden bedst.
        } catch (Exception ex) {
            throw new CalculatorException("There was an error finding the best possible board for the 'Farcia' on the carport", ex);
        }
    }

    //METODER NEDENFOR BRUGES KUN TIL TAG MED HÆLDNING!
    //Beregner vindskedernes(Facia) længde, skal modtage boardtypen(25x150 mm trykimp. bræt), og carporten som parameter.
    //Kan også beregne længden på vandbrædderne(RainWare)(19x100mm tryk imp. bræt)
    public Double calculateBoardLengthForFarciaAndRainware(Carport carport) throws CalculatorException {
        try {
            double hosliggendeKatete = (carport.getRoof().getWidth() / 2);          // Tagbredden divideres med 2 for at finde midten af gavlen
            int roofAngle = carport.getRoof().getRoofSlopeCelsius();                 // roofAngle holder hældningen på taget
            double boardLengthHypotenuse = (hosliggendeKatete) / (Math.cos(Math.toRadians(roofAngle))); //  Hypotenusen isoleres i cosinus formel for retvinklet trekanter, for at finde længden på vindskederne

            return boardLengthHypotenuse;
        } catch (Exception ex) {
            throw new CalculatorException("There was an error calculating the board length for the 'Farcia'", ex);
        }
    }

    public Double calculateHeightForRoof(Carport carport) throws CalculatorException {
        try {
            double boardLengthHypotenuse = calculateBoardLengthForFarciaAndRainware(carport); // Kalder metode for at få længden på vinskederne (hypotenusen)
            double height = (Math.sin(carport.getRoof().getWidth()) * boardLengthHypotenuse); // 

            return height;
        } catch (CalculatorException ex) {
            throw new CalculatorException("There was an error calculating the height of the roof.", ex);
        }
    }

    // Den maksimale højde på taget, og den halve bredde på gavl, bruges til at regne antal brædder.
    public Double calculateBoardsForGable(Carport carport, Material boards) throws CalculatorException {
        try {
            double halfGable = (carport.getRoof().getWidth() / 2);                  // Finder bredden på en halv gavl
            double countBoards = (halfGable * toMilimeters / boards.getWidth() * 2);// Retunere hvor mange brædder der skal bruges til gavlen. Der ganges med to fordi det både gælder for og bag

            return countBoards;
        } catch (Exception ex) {
            throw new CalculatorException("There was an error calculating the amount of boards to be used for the gable.", ex);
        }
    }

    // Regner antal lægter ud som skal ligge på begge sidder af skråtaget
    public int calculateBattensForPitchedRoof(Carport carport, TreeMap<Double, Material> boards) throws CalculatorException {
        try {
            int spaceBetweenEachBatten = 300;
            double battenWidth = boards.firstEntry().getValue().getWidth();
            double totalMeasureBetweenEachBatten = spaceBetweenEachBatten + battenWidth;
            
            double hypotenuseLength = calculateBoardLengthForFarciaAndRainware(carport);
            double countBattens = (Math.ceil(hypotenuseLength * 1000 / totalMeasureBetweenEachBatten * 2)); // Længden af vindskeden bruges til at finde ud af antallet af lægter pr. side af skråtaget. For at dække begge sider ganges der med to. Math.ceil() bruges til at runde op. 

            return (int) countBattens;
        } catch (CalculatorException ex) {
            throw new CalculatorException("There was an error calculating the amount of battens for the roof.", ex);
        }
    }

    //add tiles pr. m2, based on measure from stykliste
    public int calculateTiles(Carport carport) throws CalculatorException {
        try {
            carport.getRoof().calculateRoofDimensions(carport);
            int tilesPrM2 = 11;
            double roof = Math.ceil((carport.getRoof().getLength() * (carport.getRoof().getWidth()))) * tilesPrM2;
            
            return (int) roof;
        } catch (Exception ex) {
            throw new CalculatorException("There was an error calculating the amount of tiles for the roof.", ex);
        }
    }


    private int getUniversalBracketsQty(Carport carport, Material mat) throws CalculatorException {
        try {
            if (carport.getRoof().getRoofSlopeCelsius() == 0) {
                
                return getQTYForRafter(carport, mat);
            } else {
                return 8;
                
            }
        } catch (CalculatorException ex) {
            throw new CalculatorException("There was an error calculating the amount of universal brackets for the carport.", ex);
        }
    }

    private int calculateQtyOfBræddebolt(Carport carport, Material mat) throws CalculatorException {
        try {
            if (carport.getRoof().getRoofSlopeCelsius() == 0) {
                return getQtyOfPosts(carport) * 2;
            } else {
                return 8;
                
            }
        } catch (CalculatorException ex) {
            throw new CalculatorException("There was an error calculating the amount of braeddebolt for the carport.", ex);
        }
    }
}
