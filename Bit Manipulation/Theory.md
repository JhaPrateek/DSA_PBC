## Decimal to Binary

```java
public static String decimalToBinary(int n){
        String ans="";
        while(n!=0){
            ans=(n%2)+ans;
            n=n/2;
        }
        return ans;
    }
    // TC: O(log n) where n is the input number (number of bits)
    // SC: O(log n) for the output string
```    

## Binary to Decimal

```java
public static int binaryToDecimal(int n){
        int ans=0;
        int i=0;
        while(n!=0){
            ans=ans+((n%10)*(int)Math.pow(2,i++));
            n=n/10;
        }
        return ans;
    }
    // TC: O(d) where d is the number of binary digits
    // SC: O(1) auxiliary space
```    

## One's Complement
The one's complement of a binary number is obtained by flipping all the bits. For example, the one's complement of 13 (binary 1101) is:
  0000 1101 (binary of 13)
  1111 0010 (one's complement of 13)

## Two's Complement
The two's complement is obtained by taking the one's complement of a number and then adding 1. For example, the two's complement of 13 (binary 1101) is:
Find the one's complement:
  0000 1101 (binary of 13)
  1111 0010 (one's complement of 13)
Add 1 to the one's complement:
  1111 0011

# Bitwise Operators

## AND Operator
The AND operator is denoted by & and performs a bitwise AND operation. If both corresponding bits are 1, the resulting bit is 1; otherwise, the resulting bit is 0.
Example: 13 & 7 is:
```
   13: 1101
   7: 0111
  & -------
   5: 0101
```

## OR Operator
The OR operator is denoted by | and performs a bitwise OR operation. If either corresponding bit is 1, the resulting bit is 1; otherwise, the resulting bit is 0.
Example: 13 | 7 is:
   13: 1101
   7: 0111
   | --------
   15: 1111

## XOR Operator
The XOR operator is denoted by ^ and performs a bitwise XOR operation. If the number of 1s in corresponding bit positions is odd, the resulting bit is 1; if even, the resulting bit is 0.
Example: 13 ^ 7 is:
   13: 1101
   7: 0111
   ^ --------
   10: 1010

## NOT Operator
The NOT operator is denoted by ~ and flips all the bits in the number.
Example: ~5 is:
   5: 0000 0101
   ~5: 1111 1010 (which is -6 in two's complement)

## Shift Operators
Right Shift (>>): Shifts the bits to the right, discarding the bits that fall off and filling the leftmost bits with zeros.
Example:    13 >> 1 = 1101 >> 1 = 0110 (binary of 6)
Left Shift (<<): Shifts the bits to the left, discarding the bits that fall off and filling the rightmost bits with zeros.
Example:    13 << 1 = 1101 << 1 = 11010 (binary of 26)