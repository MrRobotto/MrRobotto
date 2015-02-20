precision mediump float;

uniform sampler2D texture; 

varying vec2 vTextureCoords;
varying vec3 vPosition;
varying vec3 vNormal;

void main() {
	vec3 lightPos = vec3(0,0,5);
    float distance = length(lightPos - vPosition);
    vec3 lightVector = normalize(lightPos - vPosition);
 
    // Calculate the dot product of the light vector and vertex normal. If the normal and light vector are
    // pointing in the same direction then it will get max illumination.
    float intensity = max(dot(normalize(vNormal), lightVector), 0.1);
 
	if (intensity > 0.75) 
		gl_FragColor = texture2D(texture, vTextureCoords);
	//else if (intensity > 0.5)
	//	gl_FragColor = 0.5 * texture2D(texture, vTextureCoords);
	else if (intensity > 0.30)
		gl_FragColor = 0.80 * texture2D(texture, vTextureCoords);
	else
		gl_FragColor = 0.1 * texture2D(texture, vTextureCoords);
}