; @title Array Counter
; @author Nicholas Ferrara
; @description counts the elements of an array and outputs the total
; sum, the number of positive odd numbers, and the number of negative
; numbers. It is written for the Sigma16 assembly language.

; Application in Java:
; int[] x = {
;         3, -6, 27, 101, 50, 0, -20, -21, 19, 6, 4, -10
; };
;
; int possum = 0;
; int negcount = 0;
; int oddcount = 0;
;
; for (int i = 0; i < x.length; i++) {
;         if (x[i] >= 0) {
;                 possum += x[i];
;
;                 if (x[i] % 2 == 1) {
;                         oddcount++;
;                 }
;         }
;         else {
;                 negcount++;
;         }
; }


; Initialisation
        lea     R1,0[R0]        ; R1 = constant 0
        lea     R2,1[R0]        ; R2 = constant 1
        lea     R3,2[R0]        ; R3 = constant 2
        load    R4,n[R0]        ; R4 = n
        lea     R5,0[R0]        ; R5 = i, initially 0
        lea     R6,0[R0]        ; R6 = possum, initially 0
        lea     R7,0[R0]        ; R7 = negcount
        lea     R8,0[R0]        ; R8 = oddcount

        ; R9 = Is the program finished bool
        ; R10 = x[i]
        ; R11 = Is this a negative number bool
        ; R12 = The result of dividing by 2
        ; R13 = Is this a positive odd number bool
        ; R15 = The modulus after dividing by 2

; Main loop
        ; Check if the loop is finished and if so break
loop    cmplt   R9,R5,R4        ; R9 = (i<n)
        jumpf   R9,done[R0]     ; If not (i<n) then exit loop
        load    R10,x[R5]       ; R10 = x[i]

        ; Go to else if negative, otherwise add x[i] to possum
        cmplt   R11,R10,R0      ; R11 = (x[i] < 0)
        jumpt   R11,neg[R0]     ; if true then skip the if logic
        add     R6,R6,R10       ; possum = possum + x[i]

        ; Check if x[i] is odd, and if so increment oddcount
        div     R12,R10,R3      ; x[i] / 2
        cmpeq   R13,R15,R2      ; if modulus from above equals 1 (odd)
        jumpf   R13,loopend[R0] ; then skip the following add
        add     R8,R8,R2        ; +1 to oddcount
        jump    loopend[R0]     ; jump to the end of the loop

        ; increment negative count if sent here
neg     add     R7,R7,R2        ; negcount = negcount + 1

        ; Move up to the next value in the for loop and start it
loopend add     R5,R5,R2        ; i = i+ 1
        jump    loop[R0]        ; go to top of loop

        ; Save the final counts and exit
done    store   R6,possum[R0]   ; save R6 into possum
        store   R7,negcount[R0] ; save R7 into negcount
        store   R8,oddcount[R0] ; save R8 into oddcount
        trap    R0,R0,R0        ; terminate


; Data area
n           data    12  ; size of array x
x           data    3   ; x[0]
            data    -6  ; x[1]
            data    27  ; x[2]
            data    101 ; x[3]
            data    50  ; x[4]
            data    0   ; x[5]
            data    -20 ; x[6]
            data    -21 ; x[7]
            data    19  ; x[8]
            data    6   ; x[9]
            data    4   ; x[10]
            data    -10 ; x[11]
possum      data    0   ; result; initial value unimportant
negcount    data    0   ; result; initial value unimportant
oddcount    data    0   ; result; initial value unimportant
