/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.utility;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MORRISON.I
 */
public class PharmFormUtils {
     //define all maps
    private Map<Integer, String> regimenCodeDescTextMap = new HashMap<>();
    private Map<Integer, String> regimenMap = new HashMap<>();
    
    
    public final static int CURRENT_REGIMEN_LINE_CONCEPT = 165708; // From Pharmacy Form 
    
    public PharmFormUtils(){
    this.fillUpRegimenCodeTextDescription();
    this.fillUpRegimenMap();
    }
    
    
     private void fillUpRegimenMap(){
        regimenMap.put(160124, "1a");//"AZT-3TC-EFV"
        regimenMap.put(1652, "1b");//"AZT-3TC-NVP"
        regimenMap.put(104565, "1c");//"TDF-FTC-EFV"
        regimenMap.put(164854, "1d");//"TDF-FTC-NVP"
        regimenMap.put(164505, "1e"); //"TDF-3TC-EFV"
        regimenMap.put(162565, "1f");//"TDF-3TC-NVP"
        regimenMap.put(817, "1g"); //"ABC-3TC-AZT" same as ABC/3TC/AZT
        regimenMap.put(165522, "1h"); //"AZT-3TC-TDF” same as TDF-3TC-AZT
       
        regimenMap.put(165681, "1m"); //"TDF-3TC-DTG"
        regimenMap.put(165682, "1n"); //"TDF-FTC-DTG
        regimenMap.put(165691, "1o"); //"ABC-3TC-DTG"
        regimenMap.put(165692, "1p"); //"ABC-FTC-DTG"

        //added latest new regimen list
        regimenMap.put(160104, "1q"); //D4T-3TC-EFV
        regimenMap.put(166179, "1r"); // ABC-FTC-EFV
        regimenMap.put(165690, "1s"); //ABC-FTC-NVP  //changed to 1s
        regimenMap.put(166181, "1u"); //ABC-3TC-TDF
        regimenMap.put(166183, "1v"); //D4T-3TC-ABC
        regimenMap.put(166185, "1w"); //AZT-TDF-NVP
        regimenMap.put(166186, "1x"); // DDI-3TC-EFV
        regimenMap.put(166187, "1z"); //AZT-3TC-DTG
        //pending concept id for DDI/3TC/ABC
        regimenMap.put(162564, "2h");//"ABC-AZT-LPV/r" change concept ID from 165530
        regimenMap.put(166188, "2i");// DDI-3TC-LPV/r
        regimenMap.put(162559, "2j"); //ABC-DDI-LPV/r
        regimenMap.put(166245, "2k"); // AZT-TDF-ATV/r
        regimenMap.put(166092, "2m"); // ABC-3TC-ATV/r
        regimenMap.put(165541, "2n"); // ABC-DDI-SQV/r
        regimenMap.put(165527, "2o"); // TDF-FTC-SQV/r
        regimenMap.put(165528, "2p"); // TDF-3TC-SQV/r
        regimenMap.put(165529, "2q"); // AZT-3TC-SQV/r
        regimenMap.put(165534, "2r"); // AZT-3TC-IDV/r
        regimenMap.put(166190, "2s"); // D4T-3TC-IDV/r
        regimenMap.put(165539, "2t"); // TDF-FTC-IDV/r
        regimenMap.put(165533, "2u");// TDF-3TC-IDV/r
        regimenMap.put(166191, "2v"); // DDI-3TC-IND/r
        regimenMap.put(165542, "2w"); // TDF-DDI-IDV/r
        regimenMap.put(166192, "2x"); // AZT-3TC-DRV/r
        regimenMap.put(166193, "2y"); // TDF-3TC-DRV/r

        regimenMap.put(165530, "3a");//"AZT-TDF-3TC-LPV/r" // formerlly 2h
        regimenMap.put(165540, "3b"); // AZT-TDF-FTC-LPV/r       
        regimenMap.put(165537, "3c");//"TDF-AZT-3TC-ATV/r" //formerly 2i
        regimenMap.put(166194, "3d"); // TDF-3TC-DTG-LPV/r
        regimenMap.put(166195, "3e"); // TDF-FTC-AZT-ATV/r
        regimenMap.put(166196, "3f"); // TDF-3TC-DTG-DRV-RTV
        regimenMap.put(166197, "3g"); // ABC-3TC-DTG-ATV/r
        regimenMap.put(166198, "3h"); // ABC-3TC-DTG-DRV-RTV
        regimenMap.put(166199, "3i"); // ABC-3TC-AZT-LPV/r
        regimenMap.put(166200, "3j"); // AZT-3TC-LPV-SQV/r
        regimenMap.put(166201, "3k"); // AZT-3TC-LPV-ATV/r
        regimenMap.put(166202, "3m"); // ABC-3TC-AZT-EFV
        regimenMap.put(166203, "3n"); // ABC-3TC-AZT-ATV/r
        regimenMap.put(166204, "3o"); // ABC-3TC-LPV-ATV/r
        regimenMap.put(166205, "3p"); // TDF-FTC-LPV-ATV/r
        regimenMap.put(165535, "3q"); // TDF-AZT-FTC-IDV/r
        regimenMap.put(165531, "3r"); // TDF-AZT-FTC-SQV/r
        regimenMap.put(165536, "3s"); // TDF-AZT-3TC-IDV/r
        regimenMap.put(165532, "3t"); // TDF-AZT-3TC-SQV/r
        regimenMap.put(166206, "3w"); // TDF-3TC-RAL
        regimenMap.put(166207, "3x"); // AZT-RAL-ATV/r                   
        regimenMap.put(165695, "3u");//AZT-3TC-RAL //formerlly 4o
        regimenMap.put(165696, "3v");//ABC-3TC-RAL // change 5g to 3v

        regimenMap.put(165523, "2a"); //"TDF-FTC-LPV/r"
        regimenMap.put(162201, "2b");//"TDF-3TC-LPV/r"
        regimenMap.put(165524, "2c"); //"TDF-FTC-ATV/r"
        regimenMap.put(164512, "2d");//"TDF-3TC-ATV/r"
        regimenMap.put(162561, "2e");//"AZT-3TC-LPV/r"
        regimenMap.put(164511, "2f");//"AZT-3TC-ATV/r"
   
        regimenMap.put(1652, "4b");//"AZT-3TC-NVP"
        regimenMap.put(162563, "4c");//"ABC-3TC-EFV"
        regimenMap.put(162199, "4d");//"ABC-3TC-NVP"
      //  regimenMap.put(817, "4e");//"AZT-3TC-ABC" Same as ABC-3TC-AZT (took it off)
        regimenMap.put(792, "4f");//"d4T-3TC-NVP"
        regimenMap.put(166074, "4g"); 
        regimenMap.put(162561, "5b"); //AZT-3TC-LPV/r //formaerlly 4n
        regimenMap.put(162200, "5a");;//"ABC-3TC-LPV/r"
        regimenMap.put(162561, "5b");;//"AZT-3TC-LPV/r"
        regimenMap.put(162560, "5c");;//"d4T-3TC-LPV/r"

        //added latest regimen
        regimenMap.put(165525, "5d"); // ddi-3TC-NVP

        regimenMap.put(165526, "1y");;//"ABC-3TC-ddi" //change 5e to 1y
        regimenMap.put(165698, "6a"); //DRV/r + 2 NRTIs + 2 NNRTI
        regimenMap.put(165700, "6b"); //DRV/r +2NRTIs
        regimenMap.put(165688, "6c"); //DRV/r-DTG + 1-2 NRTIs
        regimenMap.put(165701, "6d"); //DRV/r-RAL + 1-2NRTIs
        regimenMap.put(165697, "6e"); //DTG+2 NRTIs
        regimenMap.put(165699, "6f"); //RAL + 2 NRTIs
        
        //for drug combination
        regimenMap.put(86663, "9a");//"AZT" Concept ID didnt match. So, Changed concept id from 26 to 86663 as defined In NMRS
        regimenMap.put(78643, "9b");//3TC Concept ID didnt match. So, changed ID from 27 to 78643 as defined In NMRS
        regimenMap.put(80586, "9c");//"NVP" Concept ID didnt match. So, Changed concept id from 28 to 80586 as defined in NMRS
        regimenMap.put(630, "9d");//"AZT-3TC" Concept ID didnt match. So, Changed concept id from 29 to 630 as defined on NMRS
        regimenMap.put(165544, "9e");//"AZT-NVP" Concept ID didnt match. So, Changed concept id from 30 to 165544 as defined in NMRS
        regimenMap.put(104567, "9f");//"FTC-TDF" Concept ID didnt match. So, Changed concept id from 31 to 104567 as defined in NMRS
        regimenMap.put(161363, "9g");//"3TC-d4T"  Concept ID didnt match. So, Changed concept id from 32 to 104567 as defined in NMRS
        regimenMap.put(166075, "9h"); //"3TC-d4T" Changed the code desc from 3TC-4DT to 3TC-NVP and Created new concept for it on NMRS and replaced the initial Concpet Id of 33 to 166075
        regimenMap.put(161364, "Unknown NDR Code APINSs Instance");//TDF/3TC Missing Drug Combination without NDR Code
        regimenMap.put(165631, "Missing NDR Code from IHVN Instance"); //Dolutegravir
        regimenMap.put(1674, "Missing NDR Code frm IHVN Instance");//RIFAMPICIN/ISONIAZID/PYRAZINAMIDE/ETHAMBUTOL PROPHYLAXIS

     
        //Added By Nelson
        regimenMap.put(165257, "CTX480");//
        regimenMap.put(76488, "FLUC");
        regimenMap.put(1679, "H");
        regimenMap.put(80945, "CTX960");

        /* Added by Bright Ibezim */
        regimenMap.put(164506, "10");
        regimenMap.put(164513, "20");
        regimenMap.put(165702, "30");
        regimenMap.put(164507, "10");
        regimenMap.put(164514, "20");
        regimenMap.put(164703, "30");
        /* Added by Bright Ibezim Reason for substitusion and switch */
        regimenMap.put(102, "1");
        regimenMap.put(165048, "2");
        regimenMap.put(160559, "3");
        regimenMap.put(160567, "4");
        regimenMap.put(160561, "5");
        regimenMap.put(159834, "6");
        regimenMap.put(163523, "7");
        regimenMap.put(160566, "8");
        regimenMap.put(160569, "9");

     }
      
     
     private void fillUpRegimenCodeTextDescription(){
     
           //key is concept id, value is NDR code text
        regimenCodeDescTextMap.put(160124, "AZT-3TC-EFV");
        regimenCodeDescTextMap.put(1652, "AZT-3TC-NVP");
        regimenCodeDescTextMap.put(104565, "DF-FTC-EFV");
        regimenCodeDescTextMap.put(164854, "TDF-FTC-NVP");
        regimenCodeDescTextMap.put(164505, "TDF-3TC-EFV");
        regimenCodeDescTextMap.put(162565, "TDF-3TC-NVP");
        regimenCodeDescTextMap.put(817, "AZT-3TC-ABC");
        regimenCodeDescTextMap.put(165522, "AZT-3TC-TDF");
        regimenCodeDescTextMap.put(162563, "ABC-3TC-EFV");
        regimenCodeDescTextMap.put(165681, "TDF-3TC-DTG");
        regimenCodeDescTextMap.put(165686, "TDF-3TC-EFV400");
        regimenCodeDescTextMap.put(165682, "TDF-FTC-DTG");
        regimenCodeDescTextMap.put(165687, "TDF-FTC-EFV400");
        regimenCodeDescTextMap.put(165523, "TDF-FTC-LPV/r");
        regimenCodeDescTextMap.put(162201, "TDF-3TC-LPV/r");
        regimenCodeDescTextMap.put(165524, "TDF-FTC-ATV/r");
        regimenCodeDescTextMap.put(164512, "TDF-3TC-ATV/r");
        regimenCodeDescTextMap.put(162561, "AZT-3TC-LPV/r");
        regimenCodeDescTextMap.put(164511, "AZT-3TC-ATV/r");
        regimenCodeDescTextMap.put(165530, "AZT-TDF-3TC-LPV/r");
        regimenCodeDescTextMap.put(165537, "TDF-AZT-3TC-ATV/r");
        regimenCodeDescTextMap.put(165688, "DRV/r-DTG + 1-2 NRTIs");
        regimenCodeDescTextMap.put(160124, "AZT-3TC-EFV");
        regimenCodeDescTextMap.put(1652, "AZT-3TC-NVP");
        regimenCodeDescTextMap.put(162563, "ABC-3TC-EFV");
        regimenCodeDescTextMap.put(162199, "ABC-3TC-NVP");
        regimenCodeDescTextMap.put(817, "AZT-3TC-ABC");
        regimenCodeDescTextMap.put(792, "d4T-3TC-NVP");
        regimenCodeDescTextMap.put(165691, "ABC-3TC-DTG");
        regimenCodeDescTextMap.put(165693, "ABC-3TC-EFV400");
        regimenCodeDescTextMap.put(162200, "ABC-3TC-LPV/r");
        regimenCodeDescTextMap.put(165692, "ABC-FTC-DTG");
        regimenCodeDescTextMap.put(165694, "ABC-FTC-EFV400");
        regimenCodeDescTextMap.put(165690, "ABC-FTC-NVP");
        regimenCodeDescTextMap.put(162561, "AZT-3TC-LPV/r");
        regimenCodeDescTextMap.put(165695, "AZT-3TC-RAL");
        regimenCodeDescTextMap.put(165681, "TDF-3TC-DTG");
        regimenCodeDescTextMap.put(164505, "TDF-3TC-EFV");
        regimenCodeDescTextMap.put(165686, "TDF-3TC-EFV400");
        regimenCodeDescTextMap.put(162565, "TDF-3TC-NVP");
        regimenCodeDescTextMap.put(165682, "TDF-FTC-DTG");
        regimenCodeDescTextMap.put(104565, "TDF-FTC-EFV");
        regimenCodeDescTextMap.put(165687, "TDF-FTC-EFV400");
        regimenCodeDescTextMap.put(164854, "TDF-FTC-NVP");
        regimenCodeDescTextMap.put(162200, "ABC-3TC-LPV/r");
        regimenCodeDescTextMap.put(162561, "AZT-3TC-LPV/r");
        regimenCodeDescTextMap.put(162560, "d4T-3TC-LPV/r");
        regimenCodeDescTextMap.put(165526, "ABC-3TC-ddi");
        regimenCodeDescTextMap.put(165696, "ABC-3TC-RAL");
        regimenCodeDescTextMap.put(164511, "AZT-3TC-ATV/r");
        regimenCodeDescTextMap.put(165695, "AZT-3TC-RAL");
        regimenCodeDescTextMap.put(164512, "TDF-3TC-ATV/r");
        regimenCodeDescTextMap.put(162201, "TDF-3TC-LPV/r");
        regimenCodeDescTextMap.put(165698, "DRV/r + 2 NRTIs + 2 NNRTI");
        regimenCodeDescTextMap.put(165700, "DRV/r +2NRTIs");
        regimenCodeDescTextMap.put(165688, "DRV/r-DTG + 1-2 NRTIs");
        regimenCodeDescTextMap.put(165701, "DRV/r-RAL + 1-2 NRTIs");
        regimenCodeDescTextMap.put(165697, "DTG+2 NRTIs");
        regimenCodeDescTextMap.put(165699, "RAL + 2 NRTIs");
        regimenCodeDescTextMap.put(86663, "AZT");//"AZT" Concept ID didnt match. So, Changed concept id from 26 to 86663 as defined in NMRS
        regimenCodeDescTextMap.put(78643, "3TC");// 3TC Concept ID didnt match . So , Changed concept Id from 27 to 78643 as defined in NMRS
        regimenCodeDescTextMap.put(80586, "NVP");//"NVP" Concept ID didnt match. So, Changed concept id from 28 to 80586 as defined in NMRS
        regimenCodeDescTextMap.put(630, "AZT-3TC");//"AZT-3TC" Concept ID didnt match. So, Changed concept id from 29 to 630 as defined in NMRS
        regimenCodeDescTextMap.put(165544, "AZT-NVP");//"AZT-NVP" Concept ID didnt match. So, Changed concept id from 30 to 165544 as defined in NMRS
        regimenCodeDescTextMap.put(104567, "FTC-TDF");//"FTC-TDF" Concept ID didnt match. So, Changed concept id from 31 to 104567 as defined in NMRS
        regimenCodeDescTextMap.put(161363, "3TC-D4T");//"3TC-d4T"  Concept ID didnt match. So, Changed concept id from 32 to 104567 as defined n NMRS
        regimenCodeDescTextMap.put(166075, "3TC-NVP"); //"3TC-d4T" Changed the code desc from 3TC-4DT to 3TC-NVP and Created new concept for it on NMRS and replaced the initial Concpet Id of 33 to 166075
        regimenCodeDescTextMap.put(165257, "Cotrimoxazole 480mg"); //Defined Concept name is CTX prophylaxis. Check with Dr. Sunday for clearification
        regimenCodeDescTextMap.put(76488, "FLUCONAZOLE");//Added By Nelson
        regimenCodeDescTextMap.put(1679, "Isoniazid");//Added By Nelson
        regimenCodeDescTextMap.put(80945, "Nystatin");//Added By Nelson
        regimenCodeDescTextMap.put(161364, "TDF/3TC"); //Missing NDR Code lamivudine/fenofovir from APINS Instance
        regimenCodeDescTextMap.put(165631, "Dolutegravir");// Missing NDR Code from IHVN Instance
        regimenCodeDescTextMap.put(1674, "RIFAMPICIN/ISONIAZID/PYRAZINAMIDE/ETHAMBUTOL PROPHYLAXIS");// Missing NDR Code from IHVN Instance

        regimenCodeDescTextMap.put(71160, "CTX960");//71160 "C00"); //Cotrimoxazole 800mg 105281 No NDR Code
         
     }

    public Map<Integer, String> getRegimenCodeDescTextMap() {
        return regimenCodeDescTextMap;
    }
   
    public Map<Integer, String> getRegimenMap() {
        return regimenMap;
    }

   
     
     
    
}
