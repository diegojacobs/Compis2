/*
* Diego Jacobs 
* 13160 
*/

.data
.align 2

salida_num:    .asciz "%d \n"
salida_str:    .asciz "%f \n"
global: .space
offset: .space

.text
.align 2

.global main
.type main, %function
fib:
fib0:
	MOV R0, #1
	STR R0, [sp, #-8]	//LocalStack -8

fib2:
	LDR R1, [sp, #-4]
	STR R1, [sp, #-12]	//LocalStack -12

	SUB R0, R1, #1	//temp0 = stack[0] - 1
	STR R1, [sp, #-8]	//LocalStack -8

	SUB R0, R1, #2	//temp0 = stack[0] - 2
	LDR R2, [sp ,#-12]
	LDR R3, [sp , #-8]	//Set value stack[4]
	ADD R0, R2, R3	//temp1 = stack[8] + stack[4]
	STR R0, [sp, #-16]	//LocalStack -16

fib3:
main:
	stmfd sp!, {fp, lr}

	STR R1, [sp, #-8]	//LocalStack -8


