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

	char *string2 = (char*)malloc(MAX); //cast to char* to remove warning
    /* FIXED: malloc return null when no memory is available
     */
    if(string2 == NULL)
      return NULL;

    /* FIXED: strlen is not reliable, but string1 can be max. MAX long */
	for (a = 0; a < MAX; a += 1) {
		if(string1[a] != ' '){
			string2[b] = string1[a];
			b += 1; //since a can be max. MAX-1, and b is _eventually_ incremented on every a, this is ok
		}
	}
    string2[MAX-1]='\0'; //just to be sure

	return string2;
} 

/* TODO: imho string1 shouldn't be changed, but copied to another string
 * */
char *getlow(char *string1){
	int a;
	char c;
    //can't trust that string1 has \0!

    for(a=0; a < MAX; a++) {   
		c = string1[a];
        if(c == '\0')
          break;
		string1[a] = tolower(c);
	}

    string1[MAX-1]='\0'; //just to be sure

    return string1;
}

int isPalin(char *string1){
	int a;
	int bool = 0;
	int b = MAX-1;
	char string2[MAX];
    
    /* strlen is unreliable (->missing \0!)*/
	for (a = 0; a < MAX && b >= 0; a += 1) {
		string2[a] = string1[b];
		b -= 1;
	}
	
	string2[MAX-1] = '\0';
    string1[MAX-1] = '\0';
	
	if(strncmp(string2, string1, MAX) == 0) {
		bool = 1;
	}
	return bool;
}

void outprint(int boolean, char *input){
	if (boolean == 1) {
      /* FIXED: format string injection
       * old: printf(input);
       */
		printf("%s",input);
		printf(" ist ein Palindrom!!\n");
	}
	else {
      /* FIXED: format string injection
       * old: printf(input);
       */
		printf("%s",input);
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
    char *toFree; //because we can't change program structure
	int c, bool;
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
		if(fgets(input, MAX, stdin) == NULL)
          return EXIT_FAILURE;
		
		input[MAX-1] = '\0';
		
		strncpy(stringA, input, MAX);
		toFree=stringB = strip(stringA);
        if(toFree == NULL) //malloc failed -> don't do anything
          continue;

		strcpy(stringA, stringB);
		stringB = getlow(stringA);
		bool = isPalin(stringB);
		
		outprint(bool, input);

        free(toFree);
        toFree=NULL;
	}
	return EXIT_SUCCESS;
}
