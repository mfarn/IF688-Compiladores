package br.ufpe.cin.if688.minijava.visitor;

import br.ufpe.cin.if688.minijava.ast.*;
import br.ufpe.cin.if688.minijava.exceptions.PrintException;
import br.ufpe.cin.if688.minijava.symboltable.Class;
import br.ufpe.cin.if688.minijava.symboltable.Method;
import br.ufpe.cin.if688.minijava.symboltable.SymbolTable;
import br.ufpe.cin.if688.minijava.symboltable.Variable;


public class TypeCheckVisitor implements IVisitor<Type> {

    private SymbolTable symbolTable;

    public TypeCheckVisitor(SymbolTable st) {
        symbolTable = st;
    }

    private Class currClass;
    private Method currMethod;

    // MainClass m;
    // ClassDeclList cl;
    public Type visit(Program n) {
        n.m.accept(this);
        for (int i = 0; i < n.cl.size(); i++) {
            n.cl.elementAt(i).accept(this);
        }
        return null;
    }

    // Identifier i1,i2;
    // Statement s;
    public Type visit(MainClass n) {
        currClass = symbolTable.getClass(n.i1.s);
        currMethod = symbolTable.getMethod("main", currClass.getId());

        n.i1.accept(this);
        n.i2.accept(this);
        n.s.accept(this);

        currClass = null;
        currMethod = null;

        return null;
    }

    // Identifier i;
    // VarDeclList vl;
    // MethodDeclList ml;
    public Type visit(ClassDeclSimple n) {

        currClass = symbolTable.getClass(n.i.s);
        n.i.accept(this);
        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.elementAt(i).accept(this);
        }
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.elementAt(i).accept(this);
        }
        currClass = null;
        return null;
    }

    // Identifier i;
    // Identifier j;
    // VarDeclList vl;
    // MethodDeclList ml;
    public Type visit(ClassDeclExtends n) {
        n.i.accept(this);
        n.j.accept(this);
        currClass = symbolTable.getClass(n.i.s);
        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.elementAt(i).accept(this);
        }
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.elementAt(i).accept(this);
            currMethod = null;
        }
        if(!symbolTable.containsClass(n.j.s)){
            PrintException.idNotFound(n.j.s);
        }
        currClass = null;
        return null;
    }

    // Type t;
    // Identifier i;
    public Type visit(VarDecl n) {
        Type t = n.t.accept(this);
        n.i.accept(this);
        return t;
    }

    // Type t;
    // Identifier i;
    // FormalList fl;
    // VarDeclList vl;
    // StatementList sl;
    // Exp e;
    public Type visit(MethodDecl n) {
        currMethod = symbolTable.getMethod(n.i.s, currClass.getId());
        n.t.accept(this);
        n.i.accept(this);
        for (int i = 0; i < n.fl.size(); i++) {
            n.fl.elementAt(i).accept(this);
        }
        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.elementAt(i).accept(this);
        }
        for (int i = 0; i < n.sl.size(); i++) {
            n.sl.elementAt(i).accept(this);
        }
        if (!symbolTable.compareTypes(currMethod.type(), n.e.accept(this)))
            PrintException.typeMatch(currMethod.type(), n.e.accept(this));
        currMethod = null;
        return null;
    }

    // Type t;
    // Identifier i;
    public Type visit(Formal n) {
        Type t = n.t.accept(this);
        n.i.accept(this);
        return t;
    }

    public Type visit(IntArrayType n) {
        return n;
    }

    public Type visit(BooleanType n) { return n; }

    public Type visit(IntegerType n) {
        return n;
    }

    // String s;
    public Type visit(IdentifierType n) {
        return n;
    }

    // StatementList sl;
    public Type visit(Block n) {
        for (int i = 0; i < n.sl.size(); i++) {
            n.sl.elementAt(i).accept(this);
        }
        return null;
    }

    // Exp e;
    // Statement s1,s2;
    public Type visit(If n) {
        Type t = n.e.accept(this);
        if(!symbolTable.compareTypes(t, new BooleanType())){
            PrintException.typeMatch(new BooleanType(), t);
        }
        n.s1.accept(this);
        n.s2.accept(this);
        return null;

    }
    // Exp e;
    // Statement s;
    public Type visit(While n) {
        Type t = n.e.accept(this);
        if(!symbolTable.compareTypes(t, new BooleanType())){
            PrintException.typeMatch(new BooleanType(), t);
        }
        n.s.accept(this);
        return null;
    }

    // Exp e;
    public Type visit(Print n) {
        n.e.accept(this);
        return null;
    }

    // Identifier i;
    // Exp e;
    public Type visit(Assign n) {
        Type t1 = n.i.accept(this);
        Type t2 = n.e.accept(this);
        if(!symbolTable.compareTypes(t1, t2)){
            PrintException.typeMatch(t1, t2);
        }
        return null;
    }

    // Identifier i;
    // Exp e1,e2;
    public Type visit(ArrayAssign n) {
        if(!symbolTable.compareTypes(n.i.accept(this), new IntArrayType())){
            PrintException.typeMatch(new IntegerType(),n.i.accept(this));
        }
        if(!symbolTable.compareTypes(n.e1.accept(this), new IntegerType())){
            PrintException.typeMatch(new IntegerType(),n.e1.accept(this));
        }
        if(!symbolTable.compareTypes(n.e2.accept(this), new IntegerType())){
            PrintException.typeMatch(new IntegerType(),n.e2.accept(this));
        }
        return null;
    }


    // Exp e1,e2;
    public Type visit(And n) {
        Type t1 = n.e1.accept(this);
        Type t2 = n.e2.accept(this);
        if (!symbolTable.compareTypes(t1, new BooleanType())){
            PrintException.typeMatch(new BooleanType(), t1);
        } else if (!symbolTable.compareTypes(t2, new BooleanType())){
            PrintException.typeMatch(new BooleanType(), t2);
        }
        return new BooleanType();
    }
    // Exp e1,e2;
    public Type visit(LessThan n) {
        Type t1 = n.e1.accept(this);
        Type i1 = n.e2.accept(this);
        if (!symbolTable.compareTypes(t1, new IntegerType())){
            PrintException.typeMatch(new IntegerType(), t1);
        }else if (!symbolTable.compareTypes(i1, new IntegerType())){
            PrintException.typeMatch(new IntegerType(), i1);
        }
        return new BooleanType();
    }

    // Exp e1,e2;
    public Type visit(Plus n) {
        Type t1 = n.e1.accept(this);
        Type i1 = n.e2.accept(this);
        if (!symbolTable.compareTypes(t1, new IntegerType())){
            PrintException.typeMatch(new IntegerType(), t1);
        }else if (!symbolTable.compareTypes(i1, new BooleanType())){
            PrintException.typeMatch(new IntegerType(), i1);
        }
        return new IntegerType();
    }

    // Exp e1,e2;
    public Type visit(Minus n) {
        Type t1 = n.e1.accept(this);
        Type i1 = n.e2.accept(this);
        if (!symbolTable.compareTypes(t1, new IntegerType())){
            PrintException.typeMatch(new IntegerType(), t1);
        }else if (!symbolTable.compareTypes(i1, new IntegerType())){
            PrintException.typeMatch(new IntegerType(), i1);
        }
        return new IntegerType();
    }

    // Exp e1,e2;
    public Type visit(Times n) {
        Type t1 = n.e1.accept(this);
        Type t2 = n.e2.accept(this);
        IntegerType type = new IntegerType();
        if(!symbolTable.compareTypes(t1, type))
            PrintException.typeMatch(new IntArrayType(), t1);
        if(!symbolTable.compareTypes(t2, type))
            PrintException.typeMatch(new IntArrayType(), t2);

        return new IntegerType();
    }

    // Exp e1,e2;
    public Type visit(ArrayLookup n) {
        Type t1 = n.e1.accept(this);
        Type t2 = n.e2.accept(this);

        if(!symbolTable.compareTypes(t1, new IntArrayType()))
            PrintException.typeMatch(new IntArrayType(), t1);
        if(!symbolTable.compareTypes(t2, new IntegerType()))
            PrintException.typeMatch(new IntegerType(), t2);

        return null;
    }

    // Exp e;
    public Type visit(ArrayLength n) {
        Type t = n.e.accept(this);
        if(!symbolTable.compareTypes(t, new IntArrayType()))
            PrintException.typeMatch(new IntArrayType(), t);

        return new IntegerType();
    }

    // Exp e;
    // Identifier i;
    // ExpList el;
    public Type visit(Call n) {
        Type type = n.e.accept(this);
        if (type instanceof IdentifierType) {
            IdentifierType identifierType = (IdentifierType) type;

            Class currClass = this.currClass;

            this.currClass = this.symbolTable.getClass(identifierType.s);
            assert this.currClass != null;

            Type identifier = n.i.accept(this);

            this.currClass = currClass;
            Method method = this.symbolTable.getMethod(n.i.s, identifierType.s);
            if (method.getParamAt(n.el.size()) != null) {
                PrintException.tooFewArguments(n.i.s);
            }
            for (int i = 0; i < n.el.size(); ++i) {
                if (method.getParamAt(i) == null)
                    PrintException.tooManyArguments(n.i.s);

                if(!(symbolTable.compareTypes(method.type(),(n.el.elementAt(i).accept(this)))))
                        PrintException.typeMatch(method.type(),n.el.elementAt(i).accept(this) );
            }
            return identifier;
        }
        PrintException.idNotFound(n.i.s);
        return null;
    }
    // int i;
    public Type visit(IntegerLiteral n) {
        return new IntegerType();
    }

    public Type visit(True n) {
        return new BooleanType();
    }

    public Type visit(False n){
        return new BooleanType();
    }

    // String s;
    public Type visit(IdentifierExp n) {
        Type t = symbolTable.getVarType(currMethod, currClass, n.s);
        return t;
    }

    public Type visit(This n) {
        return new IdentifierType(currClass.getId());
    }

    // Exp e;
    public Type visit(NewArray n) {
        n.e.accept(this);
        return null;
    }

    // Identifier i;
    public Type visit(NewObject n) {
        return new IdentifierType(n.i.s);
    }

    // Exp e;
    public Type visit(Not n) {
        Type bool = n.e.accept(this);
        if(!symbolTable.compareTypes(bool, new BooleanType()))
            PrintException.typeMatch(new BooleanType(), bool);
        return new BooleanType();
    }

    // String s;
    public Type visit(Identifier n) {
        Type t1;
        if ((this.symbolTable.containsClass(n.s) && (t1 = this.symbolTable.getClass(n.s).type()) != null) ||
                (this.currClass != null && this.currClass.containsMethod(n.s) && (t1 = this.currClass.getMethod(n.s).type()) != null) ||
                (this.currClass != null && this.currClass.containsVar(n.s) && (t1 = this.currClass.getVar(n.s).type()) != null) ||
                (this.currMethod != null && ((this.currMethod.containsParam(n.s) && (t1 = this.currMethod.getParam(n.s).type()) != null) ||
                        (this.currMethod.containsVar(n.s) && (t1 = this.currMethod.getVar(n.s).type()) != null))))
            return t1;
        else if (this.currClass != null && this.currClass.parent() != null) {
            Class currClass = this.currClass;
            try {
                this.currClass = this.symbolTable.getClass(this.currClass.parent());
                return visit(n);
            } finally {
                this.currClass = currClass;
            }
        }
        PrintException.idNotFound(n.s);
        return null;
    }
}
