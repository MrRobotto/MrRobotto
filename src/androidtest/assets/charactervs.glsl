#define MAX_BONES 8
uniform mat4 bones[MAX_BONES];

uniform mat4 mvpMatrix;
uniform mat4 mvMatrix;

attribute vec3 position;
attribute vec3 normal;
attribute vec2 textureCoords;
attribute vec4 weights;
attribute vec4 bonesIndices;

varying vec3 vPosition;
varying vec3 vNormal; 
varying vec2 vTextureCoords;

vec3 applybones() {
	vec3 finalPos = vec3(0);	
	
	int index;
	mat4 bone;
	vec4 pos = vec4(position,1);
	
	index = int(bonesIndices.x);
	bone = bones[index];
	finalPos += (weights.x * bone * pos).xyz;
	index = int(bonesIndices.y);
	bone = bones[index];
	finalPos += (weights.y * bone * pos).xyz;
	index = int(bonesIndices.z);
	bone = bones[index];
	finalPos += (weights.z * bone * pos).xyz;
	index = int(bonesIndices.w);
	bone = bones[index];
	finalPos += (weights.w * bone * pos).xyz;
	
	return finalPos;
}
	
void main() {
	vec3 finalPos = applybones();
	
	vPosition = vec3(mvMatrix * vec4(finalPos,1));

    vNormal = vec3(mvMatrix * vec4(normal, 0.0));
    
    vTextureCoords = textureCoords;

	gl_Position = mvpMatrix * vec4(finalPos ,1);
}