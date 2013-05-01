##
##	Makefile for isPalindrom
##

all: isPalindrom

isPalindrom: main.o
	gcc -o isPalindrom main.o
	rm main.o

main.o: src/main.c
	gcc -std=c99 -Wno-format -D_XOPEN_SOURCE=500 -D_BSD_SOURCE -g -c src/main.c

clean:
	rm -f main.o isPalindrom


