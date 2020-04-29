package mygame;


import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Dome;
import com.jme3.scene.shape.Sphere;



public class Main extends SimpleApplication {

    public static void main(String[] args) {
       
        Main app = new Main();
        app.start();

    }
    
     
     
    Geometry pira;
    Geometry pira2;
    Geometry esfera1;
    Geometry esfera2;
    Geometry esfera3;
    Geometry esfera4;
    Quaternion q1;
    float c=4;
    Spatial Escena;
    BulletAppState fisica;
     public double angulo=0;
    @Override
    public void simpleInitApp() {       
pira = crearpiramide(15);
pira2 =crearpiramide(15);
esfera1 = crearesfera(10);
esfera2 = crearesfera(5);
esfera3 = crearesfera(3);
esfera4 = crearesfera(3);
rootNode.attachChild(pira2);
rootNode.attachChild(pira); 
rootNode.attachChild(esfera1);
rootNode.attachChild(esfera2);
rootNode.attachChild(esfera3);
rootNode.attachChild(esfera4);
q1=new Quaternion();
Spatial pro = assetManager.loadModel("Scenes/fondo.j3o");
fisica = new BulletAppState();
stateManager.attach(fisica);
fisica.setDebugEnabled(false);
esfera4.addControl(new RigidBodyControl(1));
esfera4.getControl(RigidBodyControl.class).setPhysicsLocation(new Vector3f(40, 60, 0));
esfera4.getControl(RigidBodyControl.class).setRestitution(1f);
fisica.getPhysicsSpace().add(esfera4);
rootNode.attachChild(esfera4);



Spatial suelo = ((Node)pro).getChild("terrain-fondo");
suelo.addControl(new RigidBodyControl(0));

CollisionShape colisionsuelo =CollisionShapeFactory.createDynamicMeshShape(suelo);
RigidBodyControl cuerporigidosuelo = new RigidBodyControl(colisionsuelo,0f);
((Node)pro).getChild("terrain-fondo").addControl(cuerporigidosuelo);
fisica.getPhysicsSpace().add(cuerporigidosuelo);
suelo.getControl(RigidBodyControl.class).setRestitution(0.5f);
    flyCam.setMoveSpeed(100f);
       rootNode.attachChild(pro);
    ///////////////////////////////////////////////////////////   
    AmbientLight ambient = new AmbientLight();
    ambient.setColor(ColorRGBA.White);
    rootNode.addLight(ambient);  
    } 
    ///////////////////////////////////////////////////////////
    public Geometry crearpiramide(float y){
    Dome pira1 = new Dome(Vector3f.ZERO, 2, 3, y,false);
    Geometry geom = new Geometry("piramide 1", pira1); 
    Material mat = new Material(assetManager,"Common/MatDefs/Light/Lighting.j3md");
    mat.setTexture("DiffuseMap",assetManager.loadTexture("Textures/2.jpg"));
    geom.setMaterial(mat);
    double r = Math.random()*110;
    double h = Math.random()*100;
    geom.setLocalTranslation((float) r, 0, (float) h);
    return geom;
    }
    /////////////////////////////////////////////////////////////
    public Geometry crearesfera(float x){   
        Sphere mesh3 = new Sphere(32, 32, x, false, true);
Geometry geom3 = new Geometry("A shape", mesh3); 
Material mat4 = new Material(assetManager,
    "Common/MatDefs/Light/Lighting.j3md");
mat4.setTexture("DiffuseMap", assetManager.loadTexture("Textures/4.jpg"));
geom3.setMaterial(mat4);                         
geom3.setLocalTranslation(0, 15, 0);
rootNode.attachChild(geom3);
         return geom3;
//      ////////////////////////////////////////////////////
   }
    @Override
    public void simpleUpdate(float tpf) {
        c+=tpf;
       esfera1.setLocalTranslation(8 , 40, 10);     
        float r = FastMath.DEG_TO_RAD;
         angulo = angulo + 0.2;
         double rotacion = FastMath.DEG_TO_RAD*angulo;
        int primera = 20;
        int segunda =10;
        float xpri = (float) Math.sin(rotacion)*primera;
        float ypri = (float) Math.cos(rotacion)*primera;  
        float xerma = (float) Math.sin(rotacion)*segunda;
        float yerma = (float) Math.cos(rotacion)*segunda;
        Vector3f orbita = new Vector3f (10 , xpri+40, ypri+10);
        esfera2.setLocalTranslation(orbita);
        Vector3f arma = new Vector3f(xerma, yerma, 0);
        esfera3.setLocalTranslation(orbita);
        esfera3.getLocalTranslation().addLocal(arma);
        esfera1.rotate(r,0f,0f);
        esfera2.rotate(r,0f,0f);
        }         
    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
