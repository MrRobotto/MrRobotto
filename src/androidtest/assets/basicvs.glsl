uniform mat4 mvpMatrix;
uniform mat4 model;

attribute vec3 position;

void main() {
	gl_Position = mvpMatrix * model * vec4(position ,1);
	//gl_Position = vec4(position ,1);
}