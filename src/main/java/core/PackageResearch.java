package core;

import java.lang.management.ManagementFactory;

/** Package видимо не предназначен чтобы в нем хранить классы */
public class PackageResearch {
    public PackageResearch() {
//        Package pkg = ManagementFactory.class.getPackage();
//        String packageName = pkg.getName();
//
//        System.out.println("Classes in package " + packageName + ":");
        Package[] packages = Package.getPackages();

        for (Package p : packages) {
            System.out.println(p.getName());
        }

//        for (Package aPackage : packages) {
//            if (aPackage.getName().startsWith("java.base")) {
//                try {
//                    Class<?> pkgClass = Class.forName(aPackage.getName() + "." + aPackage.getImplementationTitle());
//                    System.out.println(pkgClass.getSimpleName());
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}
