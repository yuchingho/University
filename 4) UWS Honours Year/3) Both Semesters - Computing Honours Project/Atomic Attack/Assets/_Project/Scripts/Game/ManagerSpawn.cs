using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ManagerSpawn : MonoBehaviour {

    [Space(-10), Header("Rows for UI")]
    [SerializeField] GameObject RowOne;
    [SerializeField] GameObject RowTwo;
    [SerializeField] GameObject RowThree;
    int RowNumber = 0;

    [Space( 10), Header("Spawn Elements")]
    [SerializeField] Transform SpawnLocation;
    [SerializeField] GameObject Hydrogen;
    [SerializeField] GameObject Helium;
    [SerializeField] GameObject Lithium;
    [SerializeField] GameObject Beryllium;
    [SerializeField] GameObject Boron;
    [SerializeField] GameObject Carbon;
    [SerializeField] GameObject Nitrogen;
    [SerializeField] GameObject Oxygen;
    [SerializeField] GameObject Fluorine;
    [SerializeField] GameObject Neon;
    [SerializeField] GameObject Sodium;
    [SerializeField] GameObject Magnesium;
    [SerializeField] GameObject Aluminium;
    [SerializeField] GameObject Silicon;
    [SerializeField] GameObject Phosphorus;
    [SerializeField] GameObject Sulphur;
    [SerializeField] GameObject Chlorine;
    [SerializeField] GameObject Argon;

    void Start()
    {

	}
	
	void Update() 
    {
        SwitchRows();
	}

    public void ButtonUp()   { if (RowNumber != 0) { RowNumber--; } }
    public void ButtonDown() { if (RowNumber != 2) { RowNumber++; } }
    void SwitchRows()
    {
        switch (RowNumber)
        {
            case 1:
                RowOne.SetActive  (false);
                RowTwo.SetActive  (true );
                RowThree.SetActive(false);
                break;
            case 2:
                RowOne.SetActive  (false);
                RowTwo.SetActive  (false);
                RowThree.SetActive(true );
                break;
            default:
                RowOne.SetActive  (true );
                RowTwo.SetActive  (false);
                RowThree.SetActive(false);
                break;
        }
    }

    // code that will change layers
    public void Spawn01H ()
    {
        Instantiate(Hydrogen, new Vector2(SpawnLocation.position.x + 0.8f, SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Hydrogen, new Vector2(SpawnLocation.position.x + 0.4f, SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Hydrogen, new Vector2(SpawnLocation.position.x + 0.0f, SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Hydrogen, new Vector2(SpawnLocation.position.x - 0.4f, SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Hydrogen, new Vector2(SpawnLocation.position.x - 0.8f, SpawnLocation.position.y), Quaternion.identity);
    }

    public void Spawn02He()
    {
        Instantiate(Helium, new Vector2(SpawnLocation.position.x, SpawnLocation.position.y + 4.5f), Quaternion.identity);
    }

    public void Spawn03Li()
    {
        Instantiate(Lithium, new Vector2(SpawnLocation.position.x + 0.4f, SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Lithium, new Vector2(SpawnLocation.position.x + 0.0f, SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Lithium, new Vector2(SpawnLocation.position.x - 0.4f, SpawnLocation.position.y), Quaternion.identity);
    }

    public void Spawn04Be()
    {
        Instantiate(Beryllium, new Vector2(SpawnLocation.position.x + 0.4f, SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Beryllium, new Vector2(SpawnLocation.position.x + 0.0f, SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Beryllium, new Vector2(SpawnLocation.position.x - 0.4f, SpawnLocation.position.y), Quaternion.identity);
    }
    public void Spawn05B () { }
    public void Spawn06C () { }
    public void Spawn07N () { }
    public void Spawn08O () { }
    public void Spawn09F () { }
    public void Spawn10Ne() { }
    public void Spawn11Na() { }
    public void Spawn12Mg() { }
    public void Spawn13Al() { }
    public void Spawn14Si() { }
    public void Spawn15P () { }
    public void Spawn16S () { }
    public void Spawn17Cl() { }
    public void Spawn18Ar() { }
}