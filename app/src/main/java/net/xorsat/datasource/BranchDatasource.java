package net.xorsat.datasource;

import net.xorsat.model.Branch;

import java.util.ArrayList;

public class BranchDatasource {

	public ArrayList<Branch> getList() {

		ArrayList<Branch> array_list = new ArrayList<Branch>();

		Branch branch1 = new Branch();
		branch1.setBranch_name("Korangi");
		branch1.setBranch_phone("0212566554");
		branch1.setBranch_address("ST-6/4, Sec. 24, Chamra Chowrangi, Korangi Industrial Area");
		branch1.setLatitude(24.880378);
		branch1.setLongitude(67.052693);
		array_list.add(branch1);

		Branch branch2 = new Branch();
		branch2.setBranch_name("Gulshan-e-Iqbal");
		branch2.setBranch_phone("0212566554");
		branch2.setBranch_address("36-B, Hina Centre, Gulshan-e-Iqbal");
		branch2.setLatitude(24.894393);
		branch2.setLongitude(67.108226);
		array_list.add(branch2);

		Branch branch3 = new Branch();
		branch3.setBranch_name("Clifton Branch");
		branch3.setBranch_phone("0212566554");
		branch3.setBranch_address("QM Building, Plot# BC 15, Block, Khayaban-e-Roomi, Clifton");
		branch3.setLatitude(24.914789);
		branch3.setLongitude(67.064624);
		array_list.add(branch3);

		Branch branch4 = new Branch();
		branch4.setBranch_name("Gulistan-e-Johar");
		branch4.setBranch_phone("0212566554");
		branch4.setBranch_address("Rashid Minhas Road, Gulistan-e-Joha");
		branch4.setLatitude(24.895716);
		branch4.setLongitude(67.031751);
		array_list.add(branch4);

		Branch branch5 = new Branch();
		branch5.setBranch_name("Atrium Mall");
		branch5.setBranch_phone("0212566554");
		branch5.setBranch_address("249, Staff Lines, Zaib un Nisa Street, Saddar");
		branch5.setLatitude(24.935883);
		branch5.setLongitude(67.048659);
		array_list.add(branch5);

		return array_list;
	}
}
