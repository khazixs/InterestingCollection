package com.ic.learn.algorithm.exercise;

import java.util.Stack;

public class IsPopOrder {
    public static boolean isPopOrder(int[] popOrder,int[] pushOrder){
        Stack<Integer> myStack = new Stack<>();
        if (popOrder == null||pushOrder == null){
            return false;
        }
        int popLength = popOrder.length;
        int pushLength = pushOrder.length;
        if (popLength>pushLength){
            return false;
        }
        int popIndex = 0;
        int pushIndex = 0;
        while (popIndex<popLength){
            if (myStack.empty()||myStack.peek()!=popOrder[popIndex]){
                while (pushIndex<pushLength){
                    if (pushOrder[pushIndex] == popOrder[popIndex]){
                        myStack.push(pushOrder[pushIndex]);
                        pushIndex++;
                        break;
                    }else{
                        myStack.push(pushOrder[pushIndex]);
                        pushIndex++;
                    }
                }
            }

            if (myStack.empty()||myStack.peek()!=popOrder[popIndex]){
                return false;
            }else{
                myStack.pop();
                popIndex++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] popOrder1 = new int[]{4,3,5,1,2};
        int[] popOrder2 = new int[]{4,5,3,2,1};
        int[] pushOrder = new int[]{1,2,3,4,5};
        System.out.println(isPopOrder(popOrder1,pushOrder));
        System.out.println(isPopOrder(popOrder2,pushOrder));
    }
}
