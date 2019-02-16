package com.nilay;


public class IpodInventoryManagementSystem {

    public static final String BRAZIL = "Brazil";
    public static final String ARGENTINA = "Argentina";

    public static final int IPOD_COST_IN_BRAZIL = 100;
    public static final int IPOD_COST_IN_ARGENTINA = 50;
    public static final int IPOD_SHIPPING_CHARGE = 400;

    public static void main (String[] args){
        String cost = purchaseIpod(ARGENTINA,120);
        System.out.println(cost);
    }

    private static String purchaseIpod(String location , int ipodCount) {
        int eligible  = purchaseEligible(ipodCount);

        if(eligible == -1){
            return "Out of Stock!!!";
        }
        else if(eligible == 0){
            return "Order is not in multiple of 10!!!";
        }

        String total = calculateCost(location,ipodCount);

        return total;
    }

    private static String calculateCost(String location, int ipodCount) {
        int flag = 0 ;
        if(location.equals(ARGENTINA)){
            return calculateCostOfIpodInArgentina(ipodCount,flag);
        }
        else{
            return compareCostBrazilVSArgentina(ipodCount);
        }
    }

    private static String compareCostBrazilVSArgentina(int ipodCount) {
        int flag = 1;
        String argentina = calculateCostOfIpodInArgentina(ipodCount,flag);
        String brazil = calculateCostOfIpodInBrazil(ipodCount);

        String[] argentinaCost = argentina.split(":");
        String[] brazilCost = brazil.split(":");

        int exactArgentinaIpodCost = Integer.parseInt(argentinaCost[0]);
        int exactBrazilIpodCost = Integer.parseInt(brazilCost[0]);

        if(exactArgentinaIpodCost < exactBrazilIpodCost){
            return argentina;
        }
        else{
            return brazil;
        }
    }

    private static String calculateCostOfIpodInBrazil(int ipodCount){
        int brazilInventory = 100;
        int argentinaInventory = 100;
        int ipodCost = 0;
        int remainingIpod = 0;
        int remainingIpodCost = 0;
        int totalIpodCost = 0;
        int flag = 0;
        if(ipodCount < brazilInventory){
            ipodCost = ipodCount * IPOD_COST_IN_BRAZIL;
            brazilInventory = brazilInventory - ipodCount;
            return ipodCost + ":" + brazilInventory + ":" + argentinaInventory;
        }else{
            ipodCost = brazilInventory * IPOD_COST_IN_BRAZIL;
            remainingIpod = ipodCount - brazilInventory;
            remainingIpodCost = calculateCostOfRemainingIpod(remainingIpod,ARGENTINA,flag);
            argentinaInventory = argentinaInventory - remainingIpod;
            brazilInventory = 0;
            totalIpodCost = ipodCost + remainingIpodCost;
            return totalIpodCost + ":" + brazilInventory + ":" + argentinaInventory;
        }
    }

    private static String calculateCostOfIpodInArgentina(int ipodCount , int flag){
        int brazilInventory = 100;
        int argentinaInventory = 100;
        int ipodCost = 0;
        int remainingIpod = 0;
        int remainingIpodCost = 0;
        int totalIpodCost = 0;

        if(ipodCount <= argentinaInventory && flag == 0){
            ipodCost = ipodCount * IPOD_COST_IN_ARGENTINA;
            argentinaInventory = argentinaInventory - ipodCount;
            return ipodCost + ":" + brazilInventory + ":" + argentinaInventory;
        }else{
            if(ipodCount > argentinaInventory){
                ipodCost = argentinaInventory * IPOD_COST_IN_ARGENTINA;
                remainingIpod = ipodCount - argentinaInventory;
                remainingIpodCost = calculateCostOfRemainingIpod(remainingIpod,BRAZIL,flag);
                brazilInventory = brazilInventory - remainingIpod;
                argentinaInventory = 0;
                totalIpodCost = ipodCost + remainingIpodCost;
                return totalIpodCost + ":" + brazilInventory + ":" + argentinaInventory;
            }else{
                ipodCost = ipodCount * IPOD_COST_IN_ARGENTINA;
                remainingIpodCost = calculateCostOfRemainingIpod(ipodCount,ARGENTINA,flag);
                argentinaInventory = argentinaInventory - ipodCount;
                totalIpodCost = ipodCost + remainingIpodCost;
                return totalIpodCost + ":" + brazilInventory + ":" + argentinaInventory;
            }
        }
    }

    private static int calculateCostOfRemainingIpod(int remainingIpod, String inventoryLocation, int flag) {
        if(inventoryLocation.equals(BRAZIL)){
            return ((remainingIpod * IPOD_COST_IN_BRAZIL) + ((remainingIpod/10)*IPOD_SHIPPING_CHARGE));

        }else{
            if(flag == 0){
                return ((remainingIpod * IPOD_COST_IN_ARGENTINA) + ((remainingIpod/10)*IPOD_SHIPPING_CHARGE));
            }
            else{
                return ((remainingIpod/10)*IPOD_SHIPPING_CHARGE);
            }
        }
    }

    private static int purchaseEligible(int ipodCount) {
        if(ipodCount > 200){
            return -1 ;
        }
        else if(ipodCount%10 != 0){
            return 0;
        }
        else{
            return 1;
        }
    }


}
