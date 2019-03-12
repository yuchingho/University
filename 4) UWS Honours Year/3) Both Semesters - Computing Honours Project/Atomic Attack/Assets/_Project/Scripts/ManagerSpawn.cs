using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ManagerSpawn : MonoBehaviour {

    ManagerGame ManagerGame;

    [Space(-10), Header("Rows for UI")]
    [SerializeField] Text Explanation;
    [SerializeField] GameObject RowOne;
    [SerializeField] GameObject RowTwo;
    [SerializeField] GameObject RowThree;
    [SerializeField] GameObject ButtonsUp;
    [SerializeField] GameObject ButtonsDown;
    bool ButtonDisable = false;
    int RowNumber = 0;

    [Space( 10), Header("Cannon Fodder Enemies")]
    [SerializeField] Transform  E_SpawnLocation;
    [SerializeField] Transform  E_CastleLocationOne;
    [SerializeField] Transform  E_CastleLocationTwo;
    [SerializeField] Transform  E_CastleLocationThree;
    int RowCastle = 0;
    [SerializeField] GameObject E_Gunman;
    [SerializeField] GameObject E_Swordsman;

    [Space( 10), Header("Cannon Fodder Friends")]
    [SerializeField] Transform  MissileLaunch;
    [SerializeField] Transform  F_SpawnLocation;
    [SerializeField] GameObject F_SwordsmanGO;

    [Space( 10), Header("Spawn Elements")]
    [SerializeField] GameObject Hydrogen;
    [SerializeField] GameObject Helium;
    [SerializeField] GameObject Lithium;
    [SerializeField] GameObject Beryllium;
    [Space(-10), Header("       Boron")]
    [SerializeField] GameObject Carbon;
    [SerializeField] GameObject Nitrogen;
    [SerializeField] GameObject Oxygen;
    [SerializeField] GameObject Fluorine;
    [SerializeField] GameObject Neon;
    [SerializeField] GameObject Sodium;
    [SerializeField] GameObject Magnesium;
    [Space(-10), Header("       Aluminium")]
    [SerializeField] GameObject Silicon;
    [SerializeField] GameObject Phosphorus;
    [SerializeField] GameObject Sulphur;
    [SerializeField] GameObject Chlorine;
    [SerializeField] GameObject Argon;

    [Space( 10), Header("Buttons Elements")]
    [SerializeField] Button[] Elements;

    GameObject CurrentMist;
    int MoneyFactor;

    void Start()
    {
        ManagerGame = GameObject.Find("Manager Game").GetComponent<ManagerGame>();
        Explanation.gameObject.SetActive(false);
        ManagerGame.CurrentGold = 300;
        //InvokeRepeating("CannonFodderEnemy",  1, 3);
        //InvokeRepeating("CannonFodderFriend", 0, 5);
	}
	
	void Update() 
    {
        SwitchRows();

        if (ManagerGame.CurrentGold <= 200)
        {
            ButtonRange(2);
        }

        else if (ManagerGame.CurrentGold <= 100)
        {
            ButtonRange(1);
        }

        else if (ManagerGame.CurrentGold <= 0)
        {
            ButtonRange(0);
        }

        // Whenever an Element is spawned, will disable all buttons for 2 seconds.
        /* else if (ButtonDisable == true) { ButtonRange(0); } */
    }

    void ButtonRange(int NumberStart)
    {
        for (int i = NumberStart; i < Elements.Length; i++)
        {   // ButtonDisable == false; in FlashText();
            Elements[i].GetComponent<Image>().color = new Color(200, 200, 200);
            Elements[i].interactable = false;
        }
    }

    #region UI Stuff
    public void ButtonUp()   { if (RowNumber != 0) { RowNumber--; } }
    public void ButtonDown() { if (RowNumber != 2) { RowNumber++; } }
    void SwitchRows()
    {   // For the UI Buttons.
        switch (RowNumber)
        {
            case 1:     // Second Row.
                ButtonsUp.GetComponent<Image>().color   = new Color(255, 255, 255);
                ButtonsDown.GetComponent<Image>().color = new Color(255, 255, 255);
                RowOne.SetActive  (false);
                RowTwo.SetActive  (true );
                RowThree.SetActive(false);
                break;
            case 2:     // Third Row.
                ButtonsDown.GetComponent<Image>().color = new Color(0, 0, 0);
                RowOne.SetActive  (false);
                RowTwo.SetActive  (false);
                RowThree.SetActive(true );
                break;
            default:    // First Row.
                ButtonsUp.GetComponent<Image>().color = new Color(0, 0, 0);
                RowOne.SetActive  (true );
                RowTwo.SetActive  (false);
                RowThree.SetActive(false);
                break;
        }
    }

    IEnumerator FlashText()
    {
        Explanation.gameObject.SetActive(true);
        ButtonDisable = true;
        yield return new WaitForSeconds(5);
        Explanation.gameObject.SetActive(false);
        ButtonDisable = false;
        for (int i = 0; i < Elements.Length; i++)
        {
            Elements[i].GetComponent<Image>().color = new Color(255, 255, 255);
            Elements[i].interactable = true;
        }
    }
    #endregion
    #region Money Stuff
    void MoneyBank()
    {
        switch (MoneyFactor)
        {
            case 1:
                break;
            case 2:
                break;

        }
    }

    #endregion


    #region Spawn Cannon Fodder
    void CannonFodderFriend()
    {
        Instantiate(F_SwordsmanGO, new Vector2(F_SpawnLocation.position.x, F_SpawnLocation.position.y), Quaternion.identity);
    }

    void CannonFodderEnemy()
    {
        float RandomSpace    = Random.Range(-5, 5); // For position when in Castle.
        float RandomLocation = Random.Range(0, 6);  // Simulating percentage-based spawning.
        RowCastle = (int)RandomLocation;
        switch (RowCastle)
        {
            case 3:     // First level of Castle.
                Instantiate(E_Gunman, new Vector2(E_CastleLocationOne.position.x + (RandomSpace * 0.1f),
                E_CastleLocationOne.position.y), Quaternion.identity);
                break;
            case 4:     // Second level of Castle.
                Instantiate(E_Gunman, new Vector2(E_CastleLocationTwo.position.x + (RandomSpace * 0.1f),
                E_CastleLocationTwo.position.y), Quaternion.identity);
                break;
            case 5:     // Third level of Castle.
                Instantiate(E_Gunman, new Vector2(E_CastleLocationThree.position.x + (RandomSpace * 0.1f),
                E_CastleLocationThree.position.y), Quaternion.identity);
                break;
            default:    // Ground level. Spawn E_Gunman or E_Swordsman.
                int UnitSwitch = Random.Range(0, 3);
                if (UnitSwitch == 0) { Instantiate(E_Gunman,    new Vector2(E_SpawnLocation.position.x, E_SpawnLocation.position.y), Quaternion.identity); }
                if (UnitSwitch == 1) { Instantiate(E_Swordsman, new Vector2(E_SpawnLocation.position.x, E_SpawnLocation.position.y), Quaternion.identity); }
                if (UnitSwitch == 2) { Instantiate(E_Swordsman, new Vector2(E_SpawnLocation.position.x, E_SpawnLocation.position.y), Quaternion.identity); }
                break;  // Simulating percentage-based spawning.
        }
    }
    #endregion
    #region Spawn Elements
    public void Spawn01H ()
    {
        ManagerGame.CurrentScore += 1000000;
        ManagerGame.CurrentGold -= 100;
        Explanation.text = "[ Hydrogen ] Bombs";
        StartCoroutine(FlashText());
        Instantiate(Hydrogen, new Vector2(F_SpawnLocation.position.x + 0.7f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Hydrogen, new Vector2(F_SpawnLocation.position.x       , F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Hydrogen, new Vector2(F_SpawnLocation.position.x - 0.7f, F_SpawnLocation.position.y), Quaternion.identity);
    }

    public void Spawn02He()
    {
        ManagerGame.CurrentScore += 2000000;
        ManagerGame.CurrentGold -= 200;

        Explanation.text = "[ Helium ] BLIMP Time";
        StartCoroutine(FlashText());
        Instantiate(Helium, new Vector2(F_SpawnLocation.position.x, F_SpawnLocation.position.y + 4f), Quaternion.identity);
    }

    public void Spawn03Li()
    {
        ManagerGame.CurrentScore += 3000000;
        ManagerGame.CurrentGold -= 300;

        Explanation.text = "[ Lithium ] Small Tasers ELECTRICITY";
        StartCoroutine(FlashText());
        Instantiate(Lithium, new Vector2(F_SpawnLocation.position.x + 0.5f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Lithium, new Vector2(F_SpawnLocation.position.x - 0.5f, F_SpawnLocation.position.y), Quaternion.identity);
    }

    public void Spawn04Be()
    {
        ManagerGame.CurrentScore += 4000000;
        ManagerGame.CurrentGold -= 400;

        Explanation.text = "[ Beryllium ] Blinds";
        StartCoroutine(FlashText());
        Instantiate(Beryllium, new Vector2(F_SpawnLocation.position.x + 0.5f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Beryllium, new Vector2(F_SpawnLocation.position.x - 0.5f, F_SpawnLocation.position.y), Quaternion.identity);
    }

    public void Spawn05B ()
    {
        ManagerGame.CurrentScore += 5000000;
        ManagerGame.CurrentGold -= 500;

        Explanation.text = "[ Boron ] Increases Movement Speed";
        StartCoroutine(FlashText());
        StartCoroutine(ActivateBoron());
    }

    public void Spawn06C ()
    {
        ManagerGame.CurrentScore += 6000000;
        ManagerGame.CurrentGold -= 600;

        Explanation.text = "[ Carbon ] Light Armour";
        StartCoroutine(FlashText());
        Instantiate(Carbon, new Vector2(F_SpawnLocation.position.x + 0.5f, F_SpawnLocation.position.y), Quaternion.identity);
        Instantiate(Carbon, new Vector2(F_SpawnLocation.position.x - 0.5f, F_SpawnLocation.position.y), Quaternion.identity);
    }

    public void Spawn07N ()
    {
        ManagerGame.CurrentScore += 7000000;
        ManagerGame.CurrentGold -= 700;

        Explanation.text = "[ Nitrogen ] Freezes";
        StartCoroutine(FlashText());
        CurrentMist = Nitrogen;
        StartCoroutine(MistTimer());
    }

    public void Spawn08O ()
    {
        ManagerGame.CurrentScore += 8000000;
        ManagerGame.CurrentGold -= 800;

        Explanation.text = "[ Oxygen ] Magnifies Bomb Power";
        StartCoroutine(FlashText());
        CurrentMist = Oxygen;
        StartCoroutine(MistTimer());
    }

    public void Spawn09F ()
    {
        ManagerGame.CurrentScore += 18000000;
        ManagerGame.CurrentGold -= 900;

        Explanation.text = "[ Fluorine ] Big Rocket";
        StartCoroutine(FlashText());
        Instantiate(Fluorine, MissileLaunch);
    }

    public void Spawn10Ne()
    {
        ManagerGame.CurrentScore += 20000000;
        ManagerGame.CurrentGold -= 1000;

        Explanation.text = "[ Neon ] Lightsaber Time";
        StartCoroutine(FlashText());
        Instantiate(Neon, new Vector2(F_SpawnLocation.position.x, F_SpawnLocation.position.y), Quaternion.identity);
    }

    public void Spawn11Na()
    {
        ManagerGame.CurrentScore += 11000000;
        ManagerGame.CurrentGold -= 1100;

        Explanation.text = "[ Sodium ] Big Tasers ELECTRICITY";
        StartCoroutine(FlashText());
        Instantiate(Sodium, new Vector2(F_SpawnLocation.position.x, F_SpawnLocation.position.y), Quaternion.identity);

    }

    public void Spawn12Mg()
    {
        ManagerGame.CurrentScore += 12000000;
        ManagerGame.CurrentGold -= 1200;

        Explanation.text = "[ Magnesium ] Blinds More";
        StartCoroutine(FlashText());
        Instantiate(Magnesium, new Vector2(F_SpawnLocation.position.x, F_SpawnLocation.position.y), Quaternion.identity);
    }

    public void Spawn13Al()
    {
        ManagerGame.CurrentScore += 13000000;
        ManagerGame.CurrentGold -= 1300;

        Explanation.text = "[ Aluminium ] Increases Attack Speed";
        StartCoroutine(FlashText());
        StartCoroutine(ActivateAluminium());
    }

    public void Spawn14Si()
    {
        ManagerGame.CurrentScore += 14000000;
        ManagerGame.CurrentGold -= 1400;

        Explanation.text = "[ Silicon ] Heavy Armour";
        StartCoroutine(FlashText());
        Instantiate(Silicon, new Vector2(F_SpawnLocation.position.x, F_SpawnLocation.position.y), Quaternion.identity);
    }

    public void Spawn15P ()
    {
        ManagerGame.CurrentScore += 15000000;
        ManagerGame.CurrentGold -= 1500;

        Explanation.text = "[ Phosphorus ] Burns";
        StartCoroutine(FlashText());
        CurrentMist = Phosphorus;
        StartCoroutine(MistTimer());
    }

    public void Spawn16S ()
    {
        ManagerGame.CurrentScore += 16000000;
        ManagerGame.CurrentGold -= 1600;

        Explanation.text = "[ Sulphur ] Magnifies Bomb Power More";
        StartCoroutine(FlashText());
        CurrentMist = Sulphur;
        StartCoroutine(MistTimer());
    }

    public void Spawn17Cl()
    {
        ManagerGame.CurrentScore += 17000000;
        ManagerGame.CurrentGold -= 1700;

        Explanation.text = "[ Chlorine ] Small Rocket";
        StartCoroutine(FlashText());
        Instantiate(Chlorine, MissileLaunch);
    }

    public void Spawn18Ar()
    {
        ManagerGame.CurrentScore += 18000000;
        ManagerGame.CurrentGold -= 1800;

        Explanation.text = "[ Argon ] Flamethrower Time";
        StartCoroutine(FlashText());
        Instantiate(Argon, new Vector2(F_SpawnLocation.position.x, F_SpawnLocation.position.y), Quaternion.identity);
    }

    IEnumerator ActivateBoron()
    {   // Finding all current F_Swordsman and activating Boron.
        GameObject[] AllFSwordsmen = GameObject.FindGameObjectsWithTag("Friend");
        foreach (GameObject NewSwordsman in AllFSwordsmen)
        { if (NewSwordsman != null && NewSwordsman.name == "F_Swordsman(Clone)")
            { NewSwordsman.GetComponent<F_Swordsman>().Boron = true; }
        }
        yield return new WaitForSeconds(4);
        foreach (GameObject NewSwordsman in AllFSwordsmen)
        { if (NewSwordsman != null && NewSwordsman.name == "F_Swordsman(Clone)")
            { NewSwordsman.GetComponent<F_Swordsman>().Boron = false; }
        }
    }

    IEnumerator ActivateAluminium()
    {   // Finding all current F_Swordsman and activating Aluminium.
        GameObject[] AllFSwordsmen = GameObject.FindGameObjectsWithTag("Friend");
        foreach (GameObject NewSwordsman in AllFSwordsmen)
        { if (NewSwordsman != null && NewSwordsman.name == "F_Swordsman(Clone)")
            { NewSwordsman.GetComponent<F_Swordsman>().Aluminium = true; }
        }
        yield return new WaitForSeconds(4);
        foreach (GameObject NewSwordsman in AllFSwordsmen)
        { if (NewSwordsman != null && NewSwordsman.name == "F_Swordsman(Clone)")
            { NewSwordsman.GetComponent<F_Swordsman>().Aluminium = false; }
        }
    }

    IEnumerator MistTimer()
    {
        CurrentMist.SetActive(true);
        yield return new WaitForSeconds(4);
        CurrentMist.SetActive(false);
    }
    #endregion
}