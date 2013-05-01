#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <assert.h>  
#include <unistd.h>

#define MAX 41

const char *szCommand = "<not set yet>";

char *strip(char *string1){
	int a;
	int b = 0;
	char *string2 = malloc(MAX);
	for (a = 0; a <= strlen(string1) - 1; a += 1) {
		if(string1[a] != ' '){
			string2[b] = string1[a];
			b += 1;
		}
	}
	return string2;
} 

char *getlow(char *string1){
	int a;
	char c;
	while (string1[a]) {
		c = string1[a];
		string1[a] = tolower(c);
		a += 1;
	}
	return string1;
}

int isPalin(char *string1){
	int a;
	int bool = 0;
	int b = strlen(string1) - 1;
	char string2[MAX];
	for (a = 0; a <= strlen(string1) - 1; a += 1) {
		string2[a] = string1[b];
		b -= 1;
	}
	
	string2[strlen(string1)] = '\0';
	
	if(strcmp(string2, string1) == 0) {
		bool = 1;
	}
	return bool;
}

void outprint(int boolean, char *input){
	if (boolean == 1) {
		printf(input);
		printf(" ist ein Palindrom!!\n");
	}
	else {
		printf(input);
		printf(" ist kein Palindrom!!\n");
	}
} 


void Usage(void) {
	(void) fprintf(stderr, "Usage: %s [-1]\n", szCommand);
	exit(EXIT_FAILURE);
}  

int main (int argc, char **argv) 
{
    char input[MAX];
	char stringA[MAX];
	char *stringB;
	int len, c, bool;
	int bError = 0;
	int bOption1 = 0;
		
	szCommand = argv[0];
	
	while ((c = getopt(argc, argv, "1")) != -1)
	{
		switch (c) {
			case '1':
				if (bOption1) {
					bError = 1;
					break;
				}
				bOption1 = 1;
				break;
			case '?':
				bError = 1;
				break;
			default:
				assert(0);
				break;
		}
	}
	if (bError) {
		Usage();
	}
	
	while (1) {
		
		fprintf(stdout, "Bitte ihre Eingabe : ");
		fgets(input, MAX, stdin);
		
		len = strlen(input) - 1;
		input[len] = '\0';
		
		
		strcpy(stringA, input);
		stringB = strip(stringA);
		strcpy(stringA, stringB);
		stringB = getlow(stringA);
		bool = isPalin(stringB);
		
		outprint(bool, input);
	}
	return 0;
}
